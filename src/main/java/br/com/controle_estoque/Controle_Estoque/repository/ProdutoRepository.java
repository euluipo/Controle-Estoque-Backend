package br.com.controle_estoque.Controle_Estoque.repository;

import br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO;
import br.com.controle_estoque.Controle_Estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO(p.nome, p.precoUnitario, p.unidade, p.categoria.nome) FROM Produto p ORDER BY p.nome ASC")
    List<ListaPrecoDTO> findListaDePrecos();
}