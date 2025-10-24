package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String usuario;
    private String senha;
}