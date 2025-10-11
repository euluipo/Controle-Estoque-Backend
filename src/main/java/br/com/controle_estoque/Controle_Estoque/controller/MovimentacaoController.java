package br.com.controle_estoque.Controle_Estoque.controller;

import br.com.controle_estoque.Controle_Estoque.model.Movimentacao;
import br.com.controle_estoque.Controle_Estoque.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@Tag(name = "Movimentações", description = "Endpoints para registrar entradas e saídas do estoque")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public List<Movimentacao> listarTodasMovimentacoes() {
        return movimentacaoService.listarTodas();
    }

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