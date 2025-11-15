package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) que representa um item individual
 * dentro do relatório de Balanço Físico-Financeiro.
 *
 * Cada objeto deste DTO detalha um produto específico,
 * sua quantidade e o valor total correspondente.
 *
 * A anotação {@link Data} do Lombok gera getters, setters, etc.
 * As anotações {@link NoArgsConstructor} e {@link AllArgsConstructor}
 * geram os construtores padrão e completo, respectivamente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalancoItemDTO {

    /**
     * O nome do produto.
     */
    private String nomeProduto;

    /**
     * A quantidade atual deste produto em estoque.
     */
    private int quantidadeEmEstoque;

    /**
     * O valor monetário total deste item no estoque.
     * (calculado como: preço unitário * quantidadeEmEstoque).
     */
    private BigDecimal valorTotalProduto;
}