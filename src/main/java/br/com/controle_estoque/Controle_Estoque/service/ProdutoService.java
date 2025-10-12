package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.model.Produto;
import br.com.controle_estoque.Controle_Estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoDetalhes) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoDetalhes.getNome());
            produto.setPrecoUnitario(produtoDetalhes.getPrecoUnitario());
            produto.setUnidade(produtoDetalhes.getUnidade());
            produto.setQuantidadeEmEstoque(produtoDetalhes.getQuantidadeEmEstoque());
            produto.setQuantidadeMinima(produtoDetalhes.getQuantidadeMinima());
            produto.setQuantidadeMaxima(produtoDetalhes.getQuantidadeMaxima());
            produto.setCategoria(produtoDetalhes.getCategoria());
            return produtoRepository.save(produto);
        }).orElse(null);
    }

    public boolean deletarPorId(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public void reajustarPrecos(BigDecimal percentual) {
        if (percentual == null || percentual.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("O percentual de reajuste não pode ser nulo ou zero.");
        }

        List<Produto> todosOsProdutos = produtoRepository.findAll();
        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(new BigDecimal("100")));

        for (Produto produto : todosOsProdutos) {
            BigDecimal precoAtual = produto.getPrecoUnitario();
            BigDecimal novoPreco = precoAtual.multiply(fator).setScale(2, RoundingMode.HALF_UP);
            produto.setPrecoUnitario(novoPreco);
        }

        produtoRepository.saveAll(todosOsProdutos);
    }

    @Transactional
    public Produto reajustarPrecoUnitario(Long produtoId, BigDecimal percentual) {
        if (percentual == null || percentual.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("O percentual de reajuste não pode ser nulo ou zero.");
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + produtoId));

        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(new BigDecimal("100")));

        BigDecimal precoAtual = produto.getPrecoUnitario();
        BigDecimal novoPreco = precoAtual.multiply(fator).setScale(2, RoundingMode.HALF_UP);

        produto.setPrecoUnitario(novoPreco);

        return produtoRepository.save(produto);
    }
}