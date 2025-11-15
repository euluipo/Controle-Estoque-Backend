package br.com.controle_estoque.Controle_Estoque.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * DTO (Data Transfer Object) utilizado para encapsular
 * o percentual de reajuste de preço a ser aplicado.
 *
 * Este DTO é usado nos endpoints de reajuste de preço.
 *
 * A anotação {@link Data} do Lombok gera automaticamente
 * getters, setters, toString, equals e hashCode.
 */
@Data
public class ReajustePrecoDTO {

    /**
     * O valor percentual do reajuste (ex: 10 para 10%, -5 para -5%).
     */
    private BigDecimal percentual;
}