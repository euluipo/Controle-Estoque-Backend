package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.Data;

/**
 * DTO (Data Transfer Object) que encapsula os dados
 * necessários para o registro de um novo usuário no sistema.
 *
 * A anotação {@link Data} do Lombok gera automaticamente
 * getters, setters, toString, equals e hashCode.
 */
@Data
public class RegisterRequestDTO {

    /**
     * O nome completo do usuário.
     */
    private String nome;

    /**
     * O nome de usuário (login) escolhido para autenticação.
     */
    private String usuario;

    /**
     * O endereço de e-mail do usuário.
     */
    private String email;

    /**
     * O número de telefone do usuário.
     */
    private String telefone;

    /**
     * A senha escolhida pelo usuário (em texto plano,
     * será criptografada pelo serviço antes de persistir).
     */
    private String senha;
}