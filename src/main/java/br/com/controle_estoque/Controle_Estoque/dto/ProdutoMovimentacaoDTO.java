package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa o resultado
 * de uma consulta sobre movimentações de produtos.
 *
 * É usado para identificar produtos que tiveram grande volume
 * de entradas ou saídas (ex: "Produto com Mais Entradas").
 *
 * A anotação {@link Data} do Lombok gera getters, setters, etc.
 * As anotações {@link NoArgsConstructor} e {@link AllArgsConstructor}
 * geram os construtores padrão e completo, respectively.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoMovimentacaoDTO {

    /**
     * O nome do produto associado à movimentação.
     */
    private String nomeProduto;

    /**
     * O volume total movimentado (somatório das quantidades)
     * para um tipo específico de movimentação (ex: total de entradas).
     */
    private Long totalMovimentado;
}