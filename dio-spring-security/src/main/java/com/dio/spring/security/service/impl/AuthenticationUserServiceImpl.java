package com.dio.spring.security.service.impl;

import com.dio.spring.security.service.AuthenticationUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationUserServiceImpl implements AuthenticationUserService {

    public Long getUsuarioId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return Long.valueOf(jwt.getSubject());
        }

        throw new RuntimeException("Usuário não autenticado");
    }

    public String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim("email");
        }

        throw new RuntimeException("Usuário não autenticado");
    }

    public List<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim("roles");
        }

        throw new RuntimeException("Usuário não autenticado");
    }

}
