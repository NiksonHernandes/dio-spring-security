package com.dio.spring.security.service;

import com.dio.spring.security.model.dto.UsuarioRequestDTO;
import com.dio.spring.security.model.dto.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {

    ResponseEntity<List<UsuarioResponseDTO>> findAllUsuario();
    ResponseEntity<UsuarioResponseDTO> findUsuarioById(Long userId);
    ResponseEntity<UsuarioResponseDTO> insertUsuario(UsuarioRequestDTO usuarioRequestDTO);
    ResponseEntity<Boolean> deleteUsuarioById(Long userId);
}
