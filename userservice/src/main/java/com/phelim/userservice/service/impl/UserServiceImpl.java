package com.phelim.userservice.service.impl;

import com.phelim.userservice.dto.CreateUserRequestDTO;
import com.phelim.userservice.dto.UserResponseDTO;
import com.phelim.userservice.dto.identity.Credential;
import com.phelim.userservice.dto.identity.TokenExchangeParam;
import com.phelim.userservice.dto.identity.UserCreationParam;
import com.phelim.userservice.entity.User;
import com.phelim.userservice.repository.IdentityClient;
import com.phelim.userservice.repository.UserRepository;
import com.phelim.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdentityClient identityClient;

    @Value("${idp.client-id}")
    String clientId;
    @Value("${idp.client-secret}")
    String clientSecret;

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO dto) {
        //Authorization
        var token = identityClient.exchangeClientToken(TokenExchangeParam.builder()
                .grantType("client_credentials")
                .clientSecret(clientSecret)
                .clientId(clientId)
                .scope("openid")
                .build()
        );

        log.info("=========================> Token info: ", token);

        //Call CreateUser API
        var creationResponse = identityClient.createUser(UserCreationParam.builder()
                        .username(dto.getUsername())
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .email(dto.getEmail())
                        .enabled(true)
                        .emailVerified(false)
                        .credentials(List.of(Credential.builder()
                                .type("password")
                                .temporary(false)
                                .value(dto.getPassword())
                                .build()))
                .build(), "Bearer " + token.getAccessToken());

        //Extract Use_Id
        String userId = extractUserId(creationResponse);
        log.info("=========================> UserId {}" , userId);

        //Save to DB
        User user = new User();
        user.setUserId(userId);
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDob(dto.getDob());
        user.setName(dto.getName());

        user = userRepository.save(user);
        return toDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return toDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, CreateUserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDob(dto.getDob());
        user.setName(dto.getName());

        return toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dob(user.getDob())
                .name(user.getName())
                .id(user.getId())
                .build();
    }

    //Extract User_Id
    private String extractUserId(ResponseEntity<?> response){
        List<String> locations = response.getHeaders().get("Location");
        if(locations == null || locations.isEmpty()){
            throw new IllegalStateException("Location header is missing in the response");
        }

        String location = locations.get(0);
        String[] splitedStr = location.split("/");
        return splitedStr[splitedStr.length - 1];
    }
}
