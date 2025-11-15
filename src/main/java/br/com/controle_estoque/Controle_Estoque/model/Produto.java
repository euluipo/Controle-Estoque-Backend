package br.com.controle_estoque.Controle_Estoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Entidade que representa um Produto no banco de dados.
 *
 * Contém informações sobre o item, seu preço, unidade,
 * níveis de estoque e a categoria à qual pertence.
 *
 * Mapeada para a tabela "produtos".
 *
 * A anotação {@link Data} do Lombok gera automaticamente
 * getters, setters, toString, equals e hashCode.
 */
@Entity
@Table(name = "produtos")
@Data
public class Produto {

    /**
     * Identificador único do produto.
     * Chave primária (PK) com geração automática (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome do produto. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * O preço de venda de uma unidade do produto.
     * Mapeado para a coluna "preco_unitario" e não pode ser nulo.
     */
    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    /**
     * A unidade de medida do produto (ex: "UN", "KG", "PC", "L").
     * Não pode ser nulo.
     */
    @Column(nullable = false)
    private String unidade;

    /**
     * A quantidade atual deste produto disponível em estoque.
     * Mapeado para a coluna "quantidade_em_estoque" e não pode ser nulo.
     */
    @Column(name = "quantidade_em_estoque", nullable = false)
    private int quantidadeEmEstoque;

    /**
     * O nível mínimo de estoque recomendado para este produto.
     * Usado para gerar alertas de reposição.
     * Mapeado para a coluna "quantidade_minima" e não pode ser nulo.
     */
    @Column(name = "quantidade_minima", nullable = false)
    private int quantidadeMinima;

    /**
     * O nível máximo de estoque recomendado para este produto.
     * Usado para gerar avisos de estoque excessivo.
     * Mapeado para a coluna "quantidade_maxima" e não pode ser nulo.
     */
    @Column(name = "quantidade_maxima", nullable = false)
    private int quantidadeMaxima;

    /**
     * A categoria à qual este produto pertence.
     *
     * Relacionamento Muitos-para-Um ({@link ManyToOne}) com a entidade {@link Categoria}.
     * A coluna "categoria_id" (FK) não pode ser nula.
     */
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}