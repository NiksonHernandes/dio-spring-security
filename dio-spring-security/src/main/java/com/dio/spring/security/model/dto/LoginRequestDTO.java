package com.dio.spring.security.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "O email não pode ser nulo ou vazio")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "A senha não pode ser nula ou vazia")
    private String senha;
}
