package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO;
import br.com.controle_estoque.Controle_Estoque.repository.ProdutoRepository;
import br.com.controle_estoque.Controle_Estoque.dto.BalancoGeralDTO;
import br.com.controle_estoque.Controle_Estoque.dto.BalancoItemDTO;
import br.com.controle_estoque.Controle_Estoque.dto.ProdutoAbaixoMinimoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;

@Service
public class RelatorioService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ListaPrecoDTO> gerarListaDePrecos() {
        return produtoRepository.findListaDePrecos();
    }

    public BalancoGeralDTO gerarBalancoFisicoFinanceiro() {
        List<BalancoItemDTO> itens = produtoRepository.findItensBalanco();

        BigDecimal valorTotalEstoque = itens.stream()
                .map(BalancoItemDTO::getValorTotalProduto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new BalancoGeralDTO(valorTotalEstoque, itens);
    }

    public List<ProdutoAbaixoMinimoDTO> gerarRelatorioProdutosAbaixoMinimo() {
        return produtoRepository.findProdutosAbaixoDoMinimo();
    }
}