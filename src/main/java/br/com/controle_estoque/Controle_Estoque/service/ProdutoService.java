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

/**
 * Serviço responsável pela lógica de negócios e operações CRUD
 * relacionadas à entidade {@link Produto}.
 */
@Service
public class ProdutoService {

    /**
     * Repositório responsável pelo acesso e manipulação dos dados dos produtos.
     */
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Lista todos os produtos cadastrados no banco de dados.
     *
     * @return Uma lista ({@code List<Produto>}) de todos os produtos.
     */
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    /**
     * Busca um produto específico pelo seu ID.
     *
     * @param id O ID do produto a ser buscado.
     * @return Um {@link Optional} contendo o produto se encontrado, ou {@link Optional#empty()} caso contrário.
     */
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    /**
     * Salva um novo produto no banco de dados.
     *
     * @param produto O objeto {@link Produto} a ser salvo.
     * @return O produto salvo (com o ID preenchido pela persistência).
     */
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    /**
     * Atualiza os dados de um produto existente com base no seu ID.
     *
     * @param id O ID do produto a ser atualizado.
     * @param produtoDetalhes Um objeto {@link Produto} contendo os novos dados.
     * @return O produto atualizado, ou {@code null} se o produto não foi encontrado.
     */
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

    /**
     * Deleta um produto do banco de dados pelo seu ID.
     *
     * @param id O ID do produto a ser deletado.
     * @return {@code true} se o produto foi deletado com sucesso, {@code false} se o produto não existia.
     */
    public boolean deletarPorId(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Reajusta o preço de todos os produtos com base em um percentual.
     * A operação é transacional.
     *
     * @param percentual O percentual de reajuste (ex: 10 para 10%).
     * @throws IllegalArgumentException Se o percentual for nulo ou zero.
     */
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

    /**
     * Reajusta o preço de um produto específico com base em um percentual.
     * A operação é transacional.
     *
     * @param produtoId O ID do produto a ser reajustado.
     * @param percentual O percentual de reajuste (ex: 10 para 10%).
     * @return O produto com o preço atualizado.
     * @throws IllegalArgumentException Se o percentual for nulo ou zero.
     * @throws RuntimeException Se o produto não for encontrado pelo ID.
     */
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