package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioMovimentacaoDTO {
    private ProdutoMovimentacaoDTO produtoComMaisSaidas;
    private ProdutoMovimentacaoDTO produtoComMaisEntradas;
}