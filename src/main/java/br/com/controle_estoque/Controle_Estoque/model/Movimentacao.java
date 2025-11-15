package br.com.controle_estoque.Controle_Estoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entidade que representa o histórico de uma movimentação de estoque
 * (seja entrada ou saída) no banco de dados.
 *
 * Mapeada para a tabela "movimentacoes".
 *
 * A anotação {@link Data} do Lombok gera automaticamente
 * getters, setters, toString, equals e hashCode.
 */
@Entity
@Table(name = "movimentacoes")
@Data
public class Movimentacao {

    /**
     * Identificador único da movimentação.
     * Chave primária (PK) com geração automática (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O produto que foi movimentado.
     *
     * Relacionamento Muitos-para-Um ({@link ManyToOne}) com a entidade {@link Produto}.
     * A coluna "produto_id" (FK) não pode ser nula.
     * O {@code FetchType.EAGER} indica que o produto deve ser carregado
     * juntamente com a movimentação.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    /**
     * A data e hora exatas em que a movimentação foi registrada.
     * Este campo é preenchido automaticamente pelo método {@link #prePersist()}
     * no momento da persistência.
     */
    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    /**
     * A quantidade de itens do produto que foi movimentada
     * (seja entrada ou saída).
     */
    @Column(name = "quantidade_movimentada", nullable = false)
    private int quantidadeMovimentada;

    /**
     * O tipo da movimentação ({@link TipoMovimentacao}),
     * indicando se foi uma ENTRADA ou SAIDA.
     * Armazenado como STRING no banco de dados.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    /**
     * Método de callback do JPA, executado automaticamente
     * antes de uma nova entidade ser persistida (inserida) no banco.
     *
     * Define a {@code dataMovimentacao} para o momento atual.
     */
    @PrePersist
    public void prePersist() {
        dataMovimentacao = LocalDateTime.now();
    }
}