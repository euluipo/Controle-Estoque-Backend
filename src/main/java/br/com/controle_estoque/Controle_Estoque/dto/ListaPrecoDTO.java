package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) que representa um item
 * no relatório de Lista de Preços.
 *
 * Contém informações essenciais de precificação e
 * categorização de um produto.
 *
 * A anotação {@link Data} do Lombok gera getters, setters, etc.
 * As anotações {@link NoArgsConstructor} e {@link AllArgsConstructor}
 * geram os construtores padrão e completo, respectivamente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaPrecoDTO {

    /**
     * O nome do produto.
     */
    private String nomeProduto;

    /**
     * O preço de venda unitário do produto.
     */
    private BigDecimal precoUnitario;

    /**
     * A unidade de medida do produto (ex: "UN", "KG", "PC").
     */
    private String unidade;

    /**
     * O nome da categoria à qual o produto pertence.
     */
    private String nomeCategoria;
}