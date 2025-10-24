package br.com.controle_estoque.Controle_Estoque.controller;

import br.com.controle_estoque.Controle_Estoque.model.Produto;
import br.com.controle_estoque.Controle_Estoque.service.ProdutoService;
import br.com.controle_estoque.Controle_Estoque.dto.ReajustePrecoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciar produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Endpoint para LISTAR
    @GetMapping
    public List<Produto> listarTodosProdutos() {
        return produtoService.listarTodos();
    }

    // Endpoint para BUSCAR
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para CRIAR
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvar(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    // Endpoint para ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoDetalhes) {
        Produto produtoAtualizado = produtoService.atualizar(id, produtoDetalhes);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (produtoService.deletarPorId(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para REAJUSTAR PREÇO em todos os produtos
    @PostMapping("/reajustar-preco")
    public ResponseEntity<Void> reajustarPrecoDeTodosOsProdutos(@RequestBody ReajustePrecoDTO reajusteDTO) {
        try {
            produtoService.reajustarPrecos(reajusteDTO.getPercentual());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para REAJUSTAR PREÇO de um produto específico
    @PostMapping("/{id}/reajustar-preco")
    public ResponseEntity<Produto> reajustarPrecoDeProdutoUnico(@PathVariable Long id, @RequestBody ReajustePrecoDTO reajusteDTO) {
        try {
            Produto produtoAtualizado = produtoService.reajustarPrecoUnitario(id, reajusteDTO.getPercentual());
            return ResponseEntity.ok(produtoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}