package com.phelim.userservice.service;

import com.phelim.userservice.dto.LoginRequestDto;
import com.phelim.userservice.dto.identity.TokenExchangeLoginResponse;

public interface ILoginService {
    TokenExchangeLoginResponse login(LoginRequestDto loginRequestDto);
}
