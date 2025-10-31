package br.com.controle_estoque.Controle_Estoque.repository;

import br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO;
import br.com.controle_estoque.Controle_Estoque.model.Produto;
import br.com.controle_estoque.Controle_Estoque.dto.BalancoItemDTO;
import br.com.controle_estoque.Controle_Estoque.dto.ProdutoAbaixoMinimoDTO;
import br.com.controle_estoque.Controle_Estoque.dto.ProdutosPorCategoriaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO(p.nome, p.precoUnitario, p.unidade, p.categoria.nome) FROM Produto p ORDER BY p.nome ASC")
    List<ListaPrecoDTO> findListaDePrecos();

    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.BalancoItemDTO(p.nome, p.quantidadeEmEstoque, (p.precoUnitario * p.quantidadeEmEstoque)) FROM Produto p ORDER BY p.nome ASC")
    List<BalancoItemDTO> findItensBalanco();

    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ProdutoAbaixoMinimoDTO(p.nome, p.quantidadeMinima, p.quantidadeEmEstoque) " +
            "FROM Produto p " +
            "WHERE p.quantidadeEmEstoque < p.quantidadeMinima " +
            "ORDER BY p.nome ASC")
    List<ProdutoAbaixoMinimoDTO> findProdutosAbaixoDoMinimo();

    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ProdutosPorCategoriaDTO(p.categoria.nome, COUNT(p.id)) " +
            "FROM Produto p " +
            "GROUP BY p.categoria.nome " +
            "ORDER BY p.categoria.nome ASC")
    List<ProdutosPorCategoriaDTO> countProdutosByCategoria();
}