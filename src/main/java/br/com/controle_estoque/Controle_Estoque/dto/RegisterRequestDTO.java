package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String nome;
    private String usuario;
    private String email;
    private String telefone;
    private String senha;
}