package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa um item
 * no relatório de Produtos Abaixo do Estoque Mínimo.
 *
 * Lista produtos cuja quantidade atual em estoque está
 * abaixo do nível mínimo recomendado.
 *
 * A anotação {@link Data} do Lombok gera getters, setters, etc.
 * As anotações {@link NoArgsConstructor} e {@link AllArgsConstructor}
 * geram os construtores padrão e completo, respectivamente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoAbaixoMinimoDTO {

    /**
     * O nome do produto que está com estoque baixo.
     */
    private String nomeProduto;

    /**
     * A quantidade mínima de estoque definida para este produto.
     */
    private int quantidadeMinima;

    /**
     * A quantidade atual em estoque (que é inferior à quantidade mínima).
     */
    private int quantidadeEmEstoque;
}