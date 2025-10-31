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

@RestController
@RequestMapping("/api/relatorios")
@Tag(name = "Relatórios", description = "Endpoints para geração de relatórios do sistema")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/lista-de-precos")
    public List<ListaPrecoDTO> getListaDePrecos() {
        return relatorioService.gerarListaDePrecos();
    }

    @GetMapping("/balanco-financeiro")
    public BalancoGeralDTO getBalancoFinanceiro() {
        return relatorioService.gerarBalancoFisicoFinanceiro();
    }

    @GetMapping("/produtos-abaixo-minimo")
    public List<ProdutoAbaixoMinimoDTO> getProdutosAbaixoMinimo() {
        return relatorioService.gerarRelatorioProdutosAbaixoMinimo();
    }

    @GetMapping("/produtos-por-categoria")
    public List<ProdutosPorCategoriaDTO> getProdutosPorCategoria() {
        return relatorioService.gerarRelatorioProdutosPorCategoria();
    }

    @GetMapping("/maiores-movimentacoes")
    public RelatorioMovimentacaoDTO getMaioresMovimentacoes() {
        return relatorioService.gerarRelatorioMaioresMovimentacoes();
    }
}