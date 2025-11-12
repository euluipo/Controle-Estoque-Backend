package br.com.controle_estoque.Controle_Estoque.controller;

import br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO;
import br.com.controle_estoque.Controle_Estoque.service.RelatorioService;
import br.com.controle_estoque.Controle_Estoque.dto.BalancoGeralDTO;
import br.com.controle_estoque.Controle_Estoque.dto.ProdutoAbaixoMinimoDTO;
import br.com.controle_estoque.Controle_Estoque.dto.ProdutosPorCategoriaDTO;
import br.com.controle_estoque.Controle_Estoque.dto.RelatorioMovimentacaoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST que expõe os endpoints para a geração de relatórios.
 * Utiliza o {@link RelatorioService} para processar os dados.
 */
@RestController
@RequestMapping("/api/relatorios")
@Tag(name = "Relatórios", description = "Endpoints para geração de relatórios do sistema")
public class RelatorioController {

    /**
     * Serviço injetado para lidar com a lógica de negócios dos relatórios.
     */
    @Autowired
    private RelatorioService relatorioService;

    /**
     * Endpoint para gerar um relatório de lista de preços (nome e preço unitário).
     *
     * @return Uma lista de {@link ListaPrecoDTO}.
     */
    @GetMapping("/lista-de-precos")
    public List<ListaPrecoDTO> getListaDePrecos() {
        return relatorioService.gerarListaDePrecos();
    }

    /**
     * Endpoint para gerar o balanço físico-financeiro do estoque.
     *
     * @return Um {@link BalancoGeralDTO} com o valor total e os itens detalhados.
     */
    @GetMapping("/balanco-financeiro")
    public BalancoGeralDTO getBalancoFinanceiro() {
        return relatorioService.gerarBalancoFisicoFinanceiro();
    }

    /**
     * Endpoint para gerar um relatório de produtos que estão abaixo do estoque mínimo.
     *
     * @return Uma lista de {@link ProdutoAbaixoMinimoDTO}.
     */
    @GetMapping("/produtos-abaixo-minimo")
    public List<ProdutoAbaixoMinimoDTO> getProdutosAbaixoMinimo() {
        return relatorioService.gerarRelatorioProdutosAbaixoMinimo();
    }

    /**
     * Endpoint para gerar um relatório de contagem de produtos por categoria.
     *
     * @return Uma lista de {@link ProdutosPorCategoriaDTO}.
     */
    @GetMapping("/produtos-por-categoria")
    public List<ProdutosPorCategoriaDTO> getProdutosPorCategoria() {
        return relatorioService.gerarRelatorioProdutosPorCategoria();
    }

    /**
     * Endpoint para gerar um relatório das maiores movimentações
     * (produto com mais entradas e produto com mais saídas).
     *
     * @return Um {@link RelatorioMovimentacaoDTO} consolidado.
     */
    @GetMapping("/maiores-movimentacoes")
    public RelatorioMovimentacaoDTO getMaioresMovimentacoes() {
        return relatorioService.gerarRelatorioMaioresMovimentacoes();
    }
}