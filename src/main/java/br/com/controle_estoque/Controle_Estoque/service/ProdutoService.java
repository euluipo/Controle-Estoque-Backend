package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.model.Produto;
import br.com.controle_estoque.Controle_Estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}