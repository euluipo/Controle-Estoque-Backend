package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.Data;

/**
 * DTO (Data Transfer Object) que encapsula as credenciais
 * necessárias para uma requisição de autenticação (Login).
 *
 * A anotação {@link Data} do Lombok gera automaticamente
 * getters, setters, toString, equals e hashCode.
 */
@Data
public class AuthenticationRequestDTO {
    /**
     * O nome de usuário (login) fornecido para autenticação.
     */
    private String usuario;

    /**
     * A senha fornecida para autenticação.
     */
    private String senha;
}