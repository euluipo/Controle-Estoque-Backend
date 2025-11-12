package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO;
import br.com.controle_estoque.Controle_Estoque.repository.ProdutoRepository;
import br.com.controle_estoque.Controle_Estoque.dto.BalancoGeralDTO;
import br.com.controle_estoque.Controle_Estoque.dto.BalancoItemDTO;
import br.com.controle_estoque.Controle_Estoque.dto.ProdutoAbaixoMinimoDTO;
import br.com.controle_estoque.Controle_Estoque.dto.ProdutoMovimentacaoDTO;
import br.com.controle_estoque.Controle_Estoque.dto.ProdutosPorCategoriaDTO;
import br.com.controle_estoque.Controle_Estoque.dto.RelatorioMovimentacaoDTO;
import br.com.controle_estoque.Controle_Estoque.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;

/**
 * Serviço responsável por gerar diferentes tipos de relatórios
 * relacionados ao estoque, produtos e movimentações.
 */
@Service
public class RelatorioService {

    /**
     * Repositório para consultas e operações com a tabela de produtos.
     */
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Repositório para consultas e operações com movimentações de estoque.
     */
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    /**
     * Gera um relatório de lista de preços contendo o nome do produto e seu preço unitário.
     *
     * @return Uma lista de {@link ListaPrecoDTO} com os dados dos produtos.
     */
    public List<ListaPrecoDTO> gerarListaDePrecos() {
        return produtoRepository.findListaDePrecos();
    }

    /**
     * Gera o balanço físico-financeiro do estoque, somando o valor total de
     * todos os produtos e listando os itens detalhadamente.
     *
     * @return Um {@link BalancoGeralDTO} contendo o valor total do estoque e a lista de itens.
     */
    public BalancoGeralDTO gerarBalancoFisicoFinanceiro() {
        // Busca todos os itens com suas informações de valor e quantidade
        List<BalancoItemDTO> itens = produtoRepository.findItensBalanco();

        // Calcula o valor total do estoque somando o valor total de cada produto
        BigDecimal valorTotalEstoque = itens.stream()
                .map(BalancoItemDTO::getValorTotalProduto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Retorna o balanço geral (valor total + lista de itens)
        return new BalancoGeralDTO(valorTotalEstoque, itens);
    }

    /**
     * Gera um relatório com os produtos que estão abaixo do estoque mínimo definido.
     *
     * @return Uma lista de {@link ProdutoAbaixoMinimoDTO} detalhando os produtos críticos.
     */
    public List<ProdutoAbaixoMinimoDTO> gerarRelatorioProdutosAbaixoMinimo() {
        return produtoRepository.findProdutosAbaixoDoMinimo();
    }

    /**
     * Gera um relatório agrupando os produtos por categoria e contabilizando
     * quantos há em cada uma.
     *
     * @return Uma lista de {@link ProdutosPorCategoriaDTO} com a contagem por categoria.
     */
    public List<ProdutosPorCategoriaDTO> gerarRelatorioProdutosPorCategoria() {
        return produtoRepository.countProdutosByCategoria();
    }

    /**
     * Gera um relatório das maiores movimentações do estoque, identificando o
     * produto com mais entradas e o produto com mais saídas.
     *
     * @return Um {@link RelatorioMovimentacaoDTO} contendo o produto com mais saídas e o com mais entradas.
     */
    public RelatorioMovimentacaoDTO gerarRelatorioMaioresMovimentacoes() {
        // Busca o produto com maior número de entradas
        ProdutoMovimentacaoDTO maisEntradas = movimentacaoRepository.findProdutoComMaisEntradas();

        // Busca o produto com maior número de saídas
        ProdutoMovimentacaoDTO maisSaidas = movimentacaoRepository.findProdutoComMaisSaidas();

        // Retorna o relatório consolidado com ambos os resultados
        return new RelatorioMovimentacaoDTO(maisSaidas, maisEntradas);
    }
}