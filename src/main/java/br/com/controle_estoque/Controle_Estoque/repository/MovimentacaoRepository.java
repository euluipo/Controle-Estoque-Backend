package br.com.controle_estoque.Controle_Estoque.repository;

import br.com.controle_estoque.Controle_Estoque.dto.ProdutoMovimentacaoDTO;
import br.com.controle_estoque.Controle_Estoque.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ProdutoMovimentacaoDTO(m.produto.nome, SUM(m.quantidadeMovimentada)) " +
            "FROM Movimentacao m " +
            "WHERE m.tipoMovimentacao = 'ENTRADA' " +
            "GROUP BY m.produto.nome " +
            "ORDER BY SUM(m.quantidadeMovimentada) DESC " +
            "LIMIT 1")
    ProdutoMovimentacaoDTO findProdutoComMaisEntradas();

    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ProdutoMovimentacaoDTO(m.produto.nome, SUM(m.quantidadeMovimentada)) " +
            "FROM Movimentacao m " +
            "WHERE m.tipoMovimentacao = 'SAIDA' " +
            "GROUP BY m.produto.nome " +
            "ORDER BY SUM(m.quantidadeMovimentada) DESC " +
            "LIMIT 1")
    ProdutoMovimentacaoDTO findProdutoComMaisSaidas();
}