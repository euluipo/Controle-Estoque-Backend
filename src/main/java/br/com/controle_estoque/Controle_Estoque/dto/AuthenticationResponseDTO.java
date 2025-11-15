package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO (Data Transfer Object) que encapsula a resposta
 * de uma requisição de autenticação ou registro bem-sucedida.
 *
 * A anotação {@link Data} do Lombok gera getters, setters, etc.
 * A anotação {@link Builder} implementa o padrão Builder.
 * A anotação {@link AllArgsConstructor} gera um construtor com todos os argumentos.
 */
@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseDTO {
    /**
     * O token JWT (JSON Web Token) gerado para o usuário,
     * que deve ser usado para autenticar requisições futuras.
     */
    private String token;
}