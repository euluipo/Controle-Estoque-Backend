package br.com.controle_estoque.Controle_Estoque.model;

/**
 * Enumeração que define os tipos de embalagem
 * que um produto pode ter.
 */
public enum Embalagem {
    /**
     * Embalagem do tipo Lata (ex: refrigerantes, milho).
     */
    Lata,

    /**
     * Embalagem do tipo Vidro (ex: azeite, bebidas).
     */
    Vidro,

    /**
     * Embalagem do tipo Plástico (ex: garrafas PET, sacos).
     */
    Plástico
}