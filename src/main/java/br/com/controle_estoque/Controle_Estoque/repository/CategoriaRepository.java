package br.com.controle_estoque.Controle_Estoque.repository;

import br.com.controle_estoque.Controle_Estoque.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do Spring Data JPA para a entidade {@link Categoria}.
 *
 * Esta interface estende {@link JpaRepository}, fornecendo automaticamente
 * métodos CRUD (Create, Read, Update, Delete) prontos para uso
 * para a entidade Categoria, cuja chave primária é do tipo {@link Long}.
 *
 * A anotação {@link Repository} marca esta interface como um bean de
 * repositório do Spring, permitindo a injeção de dependência.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos de consulta personalizados (Query Methods) podem ser declarados aqui.
    // Ex: Optional<Categoria> findByNome(String nome);
}