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

/**
 * Controlador REST que expõe os endpoints para operações CRUD
 * (Criar, Ler, Atualizar, Deletar) relacionadas a {@link Produto}.
 */
@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciar produtos")
public class ProdutoController {

    /**
     * Serviço injetado para lidar com a lógica de negócios dos produtos.
     */
    @Autowired
    private ProdutoService produtoService;

    /**
     * Endpoint para LISTAR todos os produtos cadastrados.
     *
     * @return Uma lista ({@code List<Produto>}) de todos os produtos.
     */
    @GetMapping
    public List<Produto> listarTodosProdutos() {
        return produtoService.listarTodos();
    }

    /**
     * Endpoint para BUSCAR um produto específico pelo seu ID.
     *
     * @param id O ID do produto a ser buscado.
     * @return Um {@link ResponseEntity} com o produto (status 200 OK) se encontrado,
     * ou status 404 Not Found caso contrário.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para CRIAR um novo produto.
     *
     * @param produto O objeto {@link Produto} enviado no corpo da requisição.
     * @return Um {@link ResponseEntity} com o produto criado (status 201 Created).
     */
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvar(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    /**
     * Endpoint para ATUALIZAR um produto existente.
     *
     * @param id O ID do produto a ser atualizado.
     * @param produtoDetalhes O objeto {@link Produto} com os novos dados.
     * @return Um {@link ResponseEntity} com o produto atualizado (status 200 OK)
     * ou status 404 Not Found se o produto não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoDetalhes) {
        Produto produtoAtualizado = produtoService.atualizar(id, produtoDetalhes);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para DELETAR um produto pelo seu ID.
     *
     * @param id O ID do produto a ser deletado.
     * @return Um {@link ResponseEntity} com status 204 No Content se deletado com sucesso,
     * ou status 404 Not Found se o produto não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (produtoService.deletarPorId(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para REAJUSTAR PREÇO em todos os produtos com base em um percentual.
     *
     * @param reajusteDTO DTO contendo o percentual de reajuste.
     * @return Um {@link ResponseEntity} com status 200 OK se o reajuste for bem-sucedido,
     * ou status 400 Bad Request se o percentual for inválido.
     */
    @PostMapping("/reajustar-preco")
    public ResponseEntity<Void> reajustarPrecoDeTodosOsProdutos(@RequestBody ReajustePrecoDTO reajusteDTO) {
        try {
            produtoService.reajustarPrecos(reajusteDTO.getPercentual());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint para REAJUSTAR PREÇO de um produto específico.
     *
     * @param id O ID do produto a ser reajustado.
     * @param reajusteDTO DTO contendo o percentual de reajuste.
     * @return Um {@link ResponseEntity} com o produto atualizado (status 200 OK),
     * ou status 400 Bad Request se o percentual for inválido.
     */
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