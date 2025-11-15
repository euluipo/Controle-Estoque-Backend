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

/**
 * Interface do Spring Data JPA para a entidade {@link Produto}.
 *
 * Além dos métodos CRUD padrão fornecidos pelo {@link JpaRepository},
 * esta interface define consultas JPQL customizadas ({@link Query})
 * para projetar dados diretamente em DTOs, alimentando os
 * relatórios do sistema.
 *
 * A anotação {@link Repository} marca esta interface como um bean de
 * repositório do Spring.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    /**
     * Executa uma consulta JPQL customizada para gerar uma lista de preços.
     *
     * Seleciona o nome, preço unitário, unidade e o nome da categoria,
     * projetando o resultado diretamente em uma lista de {@link ListaPrecoDTO}.
     * Os resultados são ordenados pelo nome do produto (ASC).
     *
     * @return Uma {@code List<ListaPrecoDTO>} com os dados da lista de preços.
     */
    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO(p.nome, p.precoUnitario, p.unidade, p.categoria.nome) FROM Produto p ORDER BY p.nome ASC")
    List<ListaPrecoDTO> findListaDePrecos();

    /**
     * Executa uma consulta JPQL customizada para gerar os itens do balanço
     * físico-financeiro do estoque.
     *
     * Seleciona o nome do produto, a quantidade em estoque e calcula
     * o valor total do produto ({@code precoUnitario * quantidadeEmEstoque}).
     * O resultado é projetado em uma lista de {@link BalancoItemDTO}
     * e ordenado pelo nome do produto (ASC).
     *
     * @return Uma {@code List<BalancoItemDTO>} com os itens do balanço.
     */
    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.BalancoItemDTO(p.nome, p.quantidadeEmEstoque, (p.precoUnitario * p.quantidadeEmEstoque)) FROM Produto p ORDER BY p.nome ASC")
    List<BalancoItemDTO> findItensBalanco();

    /**
     * Executa uma consulta JPQL customizada para o relatório de
     * produtos abaixo do estoque mínimo.
     *
     * Seleciona apenas os produtos onde a {@code quantidadeEmEstoque}
     * é menor que a {@code quantidadeMinima}.
     * Projeta o resultado (nome, qtde. mínima, qtde. em estoque) em
     * uma lista de {@link ProdutoAbaixoMinimoDTO}, ordenada pelo nome (ASC).
     *
     * @return Uma {@code List<ProdutoAbaixoMinimoDTO>} com os produtos
     * em nível crítico de estoque.
     */
    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ProdutoAbaixoMinimoDTO(p.nome, p.quantidadeMinima, p.quantidadeEmEstoque) " +
           "FROM Produto p " +
           "WHERE p.quantidadeEmEstoque < p.quantidadeMinima " +
           "ORDER BY p.nome ASC")
    List<ProdutoAbaixoMinimoDTO> findProdutosAbaixoDoMinimo();

    /**
     * Executa uma consulta JPQL customizada para contar quantos
     * produtos existem por categoria.
     *
     * Agrupa (`GROUP BY`) os produtos pelo nome da categoria e conta (`COUNT(p.id)`)
     * os produtos em cada grupo.
     * Projeta o resultado em uma lista de {@link ProdutosPorCategoriaDTO},
     * ordenada pelo nome da categoria (ASC).
     *
     * @return Uma {@code List<ProdutosPorCategoriaDTO>} com a contagem
     * de produtos por categoria.
     */
    @Query("SELECT new br.com.controle_estoque.Controle_Estoque.dto.ProdutosPorCategoriaDTO(p.categoria.nome, COUNT(p.id)) " +
           "FROM Produto p " +
           "GROUP BY p.categoria.nome " +
           "ORDER BY p.categoria.nome ASC")
    List<ProdutosPorCategoriaDTO> countProdutosByCategoria();
}