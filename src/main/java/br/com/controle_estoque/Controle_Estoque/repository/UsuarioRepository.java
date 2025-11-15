package br.com.controle_estoque.Controle_Estoque.repository;

import br.com.controle_estoque.Controle_Estoque.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface do Spring Data JPA para a entidade {@link Usuario}.
 *
 * Fornece métodos CRUD padrão para a entidade {@link Usuario} e
 * permite a definição de métodos de consulta (query methods)
 * customizados.
 *
 * A anotação {@link Repository} marca esta interface como um bean de
 * repositório do Spring.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Encontra um usuário pelo seu nome de usuário (login).
     *
     * Este é um "query method" do Spring Data JPA. Ele automaticamente
     * gera a consulta SQL ({@code WHERE usuario = ?}) com base no nome do método.
     *
     * @param usuario O nome de usuário (login) a ser procurado.
     * @return Um {@link Optional} contendo o {@link Usuario} se encontrado,
     * ou {@link Optional#empty()} caso contrário.
     */
    Optional<Usuario> findByUsuario(String usuario);
}