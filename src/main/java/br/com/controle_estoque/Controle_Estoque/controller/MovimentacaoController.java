package br.com.controle_estoque.Controle_Estoque.controller;

import br.com.controle_estoque.Controle_Estoque.model.Movimentacao;
import br.com.controle_estoque.Controle_Estoque.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expõe os endpoints para registrar e listar
 * as movimentações de estoque (entradas e saídas).
 */
@RestController
@RequestMapping("/api/movimentacoes")
@Tag(name = "Movimentações", description = "Endpoints para registrar entradas e saídas do estoque")
public class MovimentacaoController {

    /**
     * Serviço injetado para lidar com a lógica de negócios das movimentações.
     */
    @Autowired
    private MovimentacaoService movimentacaoService;

    /**
     * Endpoint para LISTAR todas as movimentações de estoque registradas.
     *
     * @return Uma lista ({@code List<Movimentacao>}) de todas as movimentações.
     */
    @GetMapping
    public List<Movimentacao> listarTodasMovimentacoes() {
        return movimentacaoService.listarTodas();
    }

    /**
     * Endpoint para REGISTRAR uma nova movimentação (entrada ou saída).
     * <p>
     * Este endpoint aciona o {@link MovimentacaoService} que atualiza
     * o estoque do produto correspondente.
     *
     * @param movimentacao O objeto {@link Movimentacao} enviado no corpo da requisição.
     * @return Um {@link ResponseEntity} com a movimentação registrada (status 201 Created),
     * ou status 400 Bad Request se a movimentação for inválida (ex: estoque insuficiente).
     */
    @PostMapping
    public ResponseEntity<Movimentacao> registrarNovaMovimentacao(@RequestBody Movimentacao movimentacao) {
        try {
            Movimentacao novaMovimentacao = movimentacaoService.registrarMovimentacao(movimentacao);
            return new ResponseEntity<>(novaMovimentacao, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}