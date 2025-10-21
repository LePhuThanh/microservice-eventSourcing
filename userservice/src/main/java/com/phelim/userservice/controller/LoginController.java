package com.phelim.userservice.controller;

import com.phelim.userservice.dto.LoginRequestDto;
import com.phelim.userservice.dto.identity.TokenExchangeLoginResponse;
import com.phelim.userservice.dto.identity.TokenExchangeResponse;
import com.phelim.userservice.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public")
public class LoginController {

    @Autowired
    ILoginService loginService;
    @PostMapping("/login")
    ResponseEntity<TokenExchangeLoginResponse> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(loginService.login(loginRequestDto));
    }
}
