package br.com.controle_estoque.Controle_Estoque.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Entidade que representa um Usuário no banco de dados.
 *
 * Mapeada para a tabela "usuarios".
 *
 * Esta classe também implementa a interface {@link UserDetails} do Spring Security,
 * servindo como o principal objeto de autenticação e autorização do sistema.
 *
 * As anotações Lombok (@Data, @Builder, @NoArgsConstructor, @AllArgsConstructor)
 * geram automaticamente os construtores, getters, setters, etc.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    /**
     * Identificador único do usuário.
     * Chave primária (PK) com geração automática (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome completo do usuário.
     */
    private String nome;

    /**
     * O nome de usuário (login) usado para autenticação.
     * Este campo deve ser único no banco de dados.
     *
     * É o campo retornado por {@link #getUsername()}.
     */
    @Column(unique = true)
    private String usuario;

    /**
     * O endereço de e-mail do usuário.
     * Este campo deve ser único no banco de dados.
     */
    @Column(unique = true)
    private String email;

    /**
     * O número de telefone de contato do usuário.
     */
    private String telefone;

    /**
     * A senha criptografada do usuário.
     *
     * É o campo retornado por {@link #getPassword()}.
     */
    private String senha;

    /**
     * Retorna as permissões (autoridades) concedidas ao usuário.
     *
     * Nesta implementação, nenhum papel (Role) ou permissão específica está
     * sendo usado, portanto, retorna uma coleção vazia.
     *
     * @return Uma coleção vazia de {@link GrantedAuthority}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    /**
     * Retorna a senha usada para autenticar o usuário.
     *
     * @return A senha ({@code senha}) do usuário.
     */
    @Override
    public String getPassword() {
        return senha;
    }

    /**
     * Retorna o nome de usuário usado para autenticar o usuário.
     *
     * @return O nome de usuário ({@code usuario}).
     */
    @Override
    public String getUsername() {
        return usuario;
    }

    /**
     * Indica se a conta do usuário expirou.
     *
     * @return {@code true} (conta nunca expira).
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica se o usuário está bloqueado ou desbloqueado.
     *
     * @return {@code true} (conta nunca está bloqueada).
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica se as credenciais do usuário (senha) expiraram.
     *
     * @return {@code true} (credenciais nunca expiram).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica se o usuário está habilitado ou desabilitado.
     *
     * @return {@code true} (usuário sempre habilitado).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}