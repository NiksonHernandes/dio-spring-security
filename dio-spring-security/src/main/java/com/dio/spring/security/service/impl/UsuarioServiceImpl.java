package com.dio.spring.security.service.impl;

import com.dio.spring.security.model.Usuario;
import com.dio.spring.security.model.dto.UsuarioRequestDTO;
import com.dio.spring.security.model.dto.UsuarioResponseDTO;
import com.dio.spring.security.repository.UsuarioRepository;
import com.dio.spring.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<List<UsuarioResponseDTO>> findAllUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok(usuarios.stream()
                .map(u ->
                        new UsuarioResponseDTO(
                                u.getId(),
                                u.getNome(),
                                u.getEmail()))
                .toList()
        );
    }

    @Override
    public ResponseEntity<UsuarioResponseDTO> findUsuarioById(Long userId) {
        Usuario usuario = usuarioRepository.findById(userId).get();
        return ResponseEntity.ok(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }

    @Override
    public ResponseEntity<UsuarioResponseDTO> insertUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        //criptografia senha
        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
        String senhaCriptografada = criptografar.encode(usuarioRequestDTO.getSenha());

        Usuario usuarioMap = new Usuario();
        usuarioMap.setNome(usuarioRequestDTO.getNome());
        usuarioMap.setEmail(usuarioRequestDTO.getEmail());
        usuarioMap.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuarioMap);

        return ResponseEntity.ok(new UsuarioResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail()));
    }

    @Override
    public ResponseEntity<Boolean> deleteUsuarioById(Long userId) {
        Optional<Usuario> usuario = usuarioRepository.findById(userId);

        if (usuario.isPresent()) {
            usuarioRepository.deleteById(userId);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}
