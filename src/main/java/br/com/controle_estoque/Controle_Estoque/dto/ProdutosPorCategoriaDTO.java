package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa um item
 * no relatório de contagem de produtos por categoria.
 *
 * Agrupa o nome da categoria com o número total de
 * produtos associados a ela.
 *
 * A anotação {@link Data} do Lombok gera getters, setters, etc.
 * As anotações {@link NoArgsConstructor} e {@link AllArgsConstructor}
 * geram os construtores padrão e completo, respectivamente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosPorCategoriaDTO {

    /**
     * O nome da categoria.
     */
    private String nomeCategoria;

    /**
     * A contagem total de produtos pertencentes a esta categoria.
     */
    private long quantidadeProdutos;
}