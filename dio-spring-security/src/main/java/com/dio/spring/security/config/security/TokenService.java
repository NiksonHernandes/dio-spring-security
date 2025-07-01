package com.dio.spring.security.config.security;

import com.dio.spring.security.model.Role;
import com.dio.spring.security.model.Usuario;
import com.dio.spring.security.model.dto.LoginResponseDTO;
import com.dio.spring.security.model.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    public LoginResponseDTO gerarToken(Usuario usuario){

        var now = Instant.now();
        var expiresIn = 3600L; //1 hora
        //86400L - 24h

        //Obtenho as roles do usuário
        var scopes = usuario.getRoles().stream()
                .map(Role::getNome)
                .map(RoleName::name)
                .collect(Collectors.joining(" "));

        //Gerar o token JWT e retornar na requisição
        var claims = JwtClaimsSet.builder()
                .issuer("mybackend") //Quem gera o token
                .subject(usuario.getId().toString()) //Quem é o usuário
                .issuedAt(now) //Data de emissão do token
                .expiresAt(now.plusSeconds(expiresIn)) //Tempo de expiração, Agora + 300seg
                .claim("authorities", List.of("ROLE_ADMIN"))
                .build();

        //Criptografo os claims junto com a chave privada
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expiresIn);
    }

}
