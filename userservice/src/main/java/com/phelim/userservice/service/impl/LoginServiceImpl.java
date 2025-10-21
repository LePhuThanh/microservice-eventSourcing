package com.phelim.userservice.service.impl;

import com.phelim.userservice.dto.LoginRequestDto;
import com.phelim.userservice.dto.identity.TokenExchangeLoginResponse;
import com.phelim.userservice.dto.identity.TokenExchangeParam;
import com.phelim.userservice.dto.identity.UserTokenExchangeParam;
import com.phelim.userservice.repository.IdentityClient;
import com.phelim.userservice.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoginServiceImpl implements ILoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private IdentityClient identityClient;
    @Value("${idp.client-id}")
    String clientId;
    @Value("${idp.client-secret}")
    String clientSecret;
    @Override
    public TokenExchangeLoginResponse login(LoginRequestDto loginRequestDto) {
        //Login
        var token = identityClient.exchangeUserToken(UserTokenExchangeParam.builder()
                .grantType("password")
                .clientSecret(clientSecret)
                .clientId(clientId)
                .scope("openid")
                .username(loginRequestDto.getUsername())
                .password(loginRequestDto.getPassword())
                .build()
        );
        log.info("=========================> Login Token Info: " + token);
        return token;
    }
}
