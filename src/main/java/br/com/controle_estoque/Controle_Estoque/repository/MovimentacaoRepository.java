package br.com.controle_estoque.Controle_Estoque.repository;

import br.com.controle_estoque.Controle_Estoque.dto.ProdutoMovimentacaoDTO;
import br.com.controle_estoque.Controle_Estoque.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface do Spring Data JPA para a entidade {@link Movimentacao}.
 *
 * Além dos métodos CRUD padrão fornecidos pelo {@link JpaRepository},
 * esta interface define consultas JPQL customizadas ({@link Query})
 * para gerar relatórios de movimentação.
 *
 * A anotação {@link Repository} marca esta interface como um bean de
 * repositório do Spring.
 */
@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    /**
     * Executa uma consulta JPQL customizada para encontrar o produto
     * com o maior volume total de movimentações do tipo 'ENTRADA'.
     *
     * A consulta agrupa as movimentações por nome de produto, soma as
     * quantidades, ordena em ordem decrescente e retorna o primeiro resultado
     * (o produto com mais entradas) em um {@link ProdutoMovimentacaoDTO}.
     *
     * @return Um {@link ProdutoMovimentacaoDTO} contendo o nome do produto
     * e o total movimentado, ou {@code null} se não houver movimentações de entrada.
     */
    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ProdutoMovimentacaoDTO(m.produto.nome, SUM(m.quantidadeMovimentada)) " +
           "FROM Movimentacao m " +
           "WHERE m.tipoMovimentacao = 'ENTRADA' " +
           "GROUP BY m.produto.nome " +
           "ORDER BY SUM(m.quantidadeMovimentada) DESC " +
           "LIMIT 1")
    ProdutoMovimentacaoDTO findProdutoComMaisEntradas();

    /**
     * Executa uma consulta JPQL customizada para encontrar o produto
     * com o maior volume total de movimentações do tipo 'SAIDA'.
     *
     * A consulta agrupa as movimentações por nome de produto, soma as
     * quantidades, ordena em ordem decrescente e retorna o primeiro resultado
     * (o produto com mais saídas) em um {@link ProdutoMovimentacaoDTO}.
     *
     * @return Um {@link ProdutoMovimentacaoDTO} contendo o nome do produto
     * e o total movimentado, ou {@code null} se não houver movimentações de saída.
     */
    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ProdutoMovimentacaoDTO(m.produto.nome, SUM(m.quantidadeMovimentada)) " +
           "FROM Movimentacao m " +
           "WHERE m.tipoMovimentacao = 'SAIDA' " +
           "GROUP BY m.produto.nome " +
           "ORDER BY SUM(m.quantidadeMovimentada) DESC " +
           "LIMIT 1")
    ProdutoMovimentacaoDTO findProdutoComMaisSaidas();
}