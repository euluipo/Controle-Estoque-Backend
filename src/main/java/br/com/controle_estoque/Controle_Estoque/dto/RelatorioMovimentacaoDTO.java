package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que consolida o relatório
 * das maiores movimentações de estoque.
 *
 * Identifica o produto com o maior volume de saídas e
 * o produto com o maior volume de entradas.
 *
 * A anotação {@link Data} do Lombok gera getters, setters, etc.
 * As anotações {@link NoArgsConstructor} e {@link AllArgsConstructor}
 * geram os construtores padrão e completo, respectivamente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioMovimentacaoDTO {

    /**
     * Detalhes do produto que teve o maior volume de SAÍDAS
     * ({@link ProdutoMovimentacaoDTO}).
     */
    private ProdutoMovimentacaoDTO produtoComMaisSaidas;

    /**
     * Detalhes do produto que teve o maior volume de ENTRADAS
     * ({@link ProdutoMovimentacaoDTO}).
     */
    private ProdutoMovimentacaoDTO produtoComMaisEntradas;
}