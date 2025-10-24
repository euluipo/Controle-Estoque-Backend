package br.com.controle_estoque.Controle_Estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoAbaixoMinimoDTO {
    private String nomeProduto;
    private int quantidadeMinima;
    private int quantidadeEmEstoque;
}