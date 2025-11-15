package br.com.controle_estoque.Controle_Estoque.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidade que representa uma Categoria de produto no banco de dados.
 *
 * Mapeada para a tabela "categorias".
 *
 * A anotação {@link Data} do Lombok gera automaticamente
 * getters, setters, toString, equals e hashCode.
 */
@Entity
@Table(name = "categorias")
@Data
public class Categoria {

    /**
     * Identificador único da categoria.
     * Chave primária (PK) com geração automática (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome da categoria.
     * Não pode ser nulo e deve ser único no banco de dados.
     */
    @Column(nullable = false, unique = true)
    private String nome;

    /**
     * O tamanho (enum) associado aos produtos desta categoria
     * (ex: PEQUENO, MEDIO, GRANDE).
     * Armazenado como STRING no banco de dados.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tamanho tamanho;

    /**
     * O tipo de embalagem (enum) associado aos produtos desta categoria
     * (ex: CAIXA, PACOTE, FARDO).
     * Armazenado como STRING no banco de dados.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Embalagem embalagem;
}