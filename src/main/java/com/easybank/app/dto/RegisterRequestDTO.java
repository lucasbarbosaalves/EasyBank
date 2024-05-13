package com.easybank.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "O nome não pode estar vazio") String name,
        @Email @NotBlank(message = "O e-mail não pode estar vazio") String email,
        @NotBlank(message = "A senha não pode estar vazia") String password,
        @NotBlank(message = "O cpf não pode estar vazio") @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos") String cpf,
        @NotBlank(message = "O endereço deve ser preenchido") String address) {
}
