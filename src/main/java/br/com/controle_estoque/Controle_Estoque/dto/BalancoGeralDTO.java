package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO (Data Transfer Object) que representa o relatório
 * de Balanço Físico-Financeiro geral do estoque.
 *
 * Este objeto consolida o valor monetário total de
 * todo o estoque e a lista detalhada de cada item
 * que compõe esse balanço.
 *
 * A anotação {@link Data} do Lombok gera getters, setters, etc.
 * As anotações {@link NoArgsConstructor} e {@link AllArgsConstructor}
 * geram os construtores padrão e completo, respectivamente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalancoGeralDTO {

    /**
     * O valor monetário total do estoque, calculado
     * pela soma do (preço * quantidade) de todos os itens.
     */
    private BigDecimal valorTotalEstoque;

    /**
     * A lista detalhada de itens ({@link BalancoItemDTO})
     * que compõem o balanço de estoque.
     */
    private List<BalancoItemDTO> itens;
}