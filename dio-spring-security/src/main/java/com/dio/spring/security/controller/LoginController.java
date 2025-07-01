package com.dio.spring.security.controller;

import com.dio.spring.security.model.dto.LoginRequestDTO;
import com.dio.spring.security.model.dto.LoginResponseDTO;
import com.dio.spring.security.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private LoginService loginService;

    @PostMapping()
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        try {
            return loginService.login(loginRequestDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
