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

    // Repositório responsável pelo acesso e manipulação dos dados dos produtos
    @Autowired
    private ProdutoRepository produtoRepository;

    // Função: Listar todos os produtos cadastrados no banco de dados.
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Função: Buscar um produto específico pelo seu ID.
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // Função: Salvar um novo produto no banco de dados.
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Função: Atualizar os dados de um produto existente.
    public Produto atualizar(Long id, Produto produtoDetalhes) {
        return produtoRepository.findById(id).map(produto -> {
            // Atualiza os campos principais do produto
            produto.setNome(produtoDetalhes.getNome());
            produto.setPrecoUnitario(produtoDetalhes.getPrecoUnitario());
            produto.setUnidade(produtoDetalhes.getUnidade());
            produto.setQuantidadeEmEstoque(produtoDetalhes.getQuantidadeEmEstoque());
            produto.setQuantidadeMinima(produtoDetalhes.getQuantidadeMinima());
            produto.setQuantidadeMaxima(produtoDetalhes.getQuantidadeMaxima());
            produto.setCategoria(produtoDetalhes.getCategoria());

            // Salva e retorna o produto atualizado
            return produtoRepository.save(produto);
        }).orElse(null);
    }

    // Função: Deletar um produto do banco de dados pelo seu ID.
    public boolean deletarPorId(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Função: Reajustar o preço de todos os produtos com base em um percentual.
    @Transactional
    public void reajustarPrecos(BigDecimal percentual) {
        if (percentual == null || percentual.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("O percentual de reajuste não pode ser nulo ou zero.");
        }

        List<Produto> todosOsProdutos = produtoRepository.findAll();
        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(new BigDecimal("100")));

        // Aplica o reajuste percentual em cada produto
        for (Produto produto : todosOsProdutos) {
            BigDecimal precoAtual = produto.getPrecoUnitario();
            BigDecimal novoPreco = precoAtual.multiply(fator).setScale(2, RoundingMode.HALF_UP);
            produto.setPrecoUnitario(novoPreco);
        }

        // Salva todos os produtos com os novos preços
        produtoRepository.saveAll(todosOsProdutos);
    }

    // Função: Reajustar o preço de um produto específico com base em um percentual.
    @Transactional
    public Produto reajustarPrecoUnitario(Long produtoId, BigDecimal percentual) {
        if (percentual == null || percentual.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("O percentual de reajuste não pode ser nulo ou zero.");
        }

        // Busca o produto no banco e valida sua existência
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + produtoId));

        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(new BigDecimal("100")));
        BigDecimal precoAtual = produto.getPrecoUnitario();
        BigDecimal novoPreco = precoAtual.multiply(fator).setScale(2, RoundingMode.HALF_UP);

        // Atualiza o valor do produto e salva
        produto.setPrecoUnitario(novoPreco);
        return produtoRepository.save(produto);
    }
}