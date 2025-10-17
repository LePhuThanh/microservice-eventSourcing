package com.phelim.userservice.service;

import com.phelim.userservice.dto.CreateUserRequestDTO;
import com.phelim.userservice.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(CreateUserRequestDTO dto);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, CreateUserRequestDTO dto);
    void deleteUser(Long id);

}
