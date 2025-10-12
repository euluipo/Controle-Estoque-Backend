package br.com.controle_estoque.Controle_Estoque.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ReajustePrecoDTO {
    private BigDecimal percentual;
}