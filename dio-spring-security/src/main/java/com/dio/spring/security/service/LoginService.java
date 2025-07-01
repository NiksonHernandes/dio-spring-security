package com.dio.spring.security.service;

import com.dio.spring.security.model.dto.LoginRequestDTO;
import com.dio.spring.security.model.dto.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO);
}
