package com.dio.spring.security.service.impl;

import com.dio.spring.security.config.security.TokenService;
import com.dio.spring.security.model.Usuario;
import com.dio.spring.security.model.dto.LoginRequestDTO;
import com.dio.spring.security.model.dto.LoginResponseDTO;
import com.dio.spring.security.repository.UsuarioRepository;
import com.dio.spring.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(loginRequestDTO.getEmail());

        //Verifico se o user existe no BD e se a senha é igual a criptorafada no BD
        if (usuario.isEmpty() || !usuario.get().isLoginCorrect(loginRequestDTO, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("E-mail or password is invalid!");
        }

        var jwtValue = tokenService.gerarToken(usuario.get());

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue.getAccessToken(), jwtValue.getExpiresIn()));
    }
}
