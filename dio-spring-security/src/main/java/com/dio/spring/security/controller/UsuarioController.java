package com.dio.spring.security.controller;

import com.dio.spring.security.model.dto.UsuarioRequestDTO;
import com.dio.spring.security.model.dto.UsuarioResponseDTO;
import com.dio.spring.security.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/inserir")
    public ResponseEntity<UsuarioResponseDTO> inserirUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.insertUsuario(usuarioRequestDTO);
    }

    @GetMapping("/find-usuario-by-id")
    public ResponseEntity<UsuarioResponseDTO> findUsuarioById(@RequestParam @Valid Long usuarioId) {
        return usuarioService.findUsuarioById(usuarioId);
    }

    @GetMapping("/find-all-usuario")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> findAllUsuario() {
        return usuarioService.findAllUsuario();
    }

    @DeleteMapping("/delete-usuario-by-id")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Boolean> deleteUsuarioById(@RequestParam @Valid Long userId) {
        return usuarioService.deleteUsuarioById(userId);
    }
}
