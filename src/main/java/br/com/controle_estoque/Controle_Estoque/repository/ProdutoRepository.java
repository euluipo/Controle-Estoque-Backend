package br.com.controle_estoque.Controle_Estoque.repository;


import br.com.controle_estoque.Controle_Estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}