package com.dio.spring.security.service;

import java.util.ArrayList;
import java.util.List;

public interface AuthenticationUserService {
    Long getUsuarioId();
    String getEmail();
    List<String> getRoles = new ArrayList<>();
}
