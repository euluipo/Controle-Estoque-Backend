package br.com.controle_estoque.Controle_Estoque.model;

/**
 * Enumeração que define os tipos de movimentação de estoque.
 */
public enum TipoMovimentacao {
    /**
     * Representa uma entrada de produtos no estoque
     * (ex: compra de fornecedor, devolução de cliente).
     */
    ENTRADA,

    /**
     * Representa uma saída de produtos do estoque
     * (ex: venda, perda, devolução ao fornecedor).
     */
    SAIDA
}