package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.model.Movimentacao;
import br.com.controle_estoque.Controle_Estoque.model.Produto;
import br.com.controle_estoque.Controle_Estoque.model.TipoMovimentacao;
import br.com.controle_estoque.Controle_Estoque.repository.MovimentacaoRepository;
import br.com.controle_estoque.Controle_Estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoService {

    // Repositório responsável por manipular os dados das movimentações
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    // Repositório responsável pelo acesso e atualização de dados dos produtos
    @Autowired
    private ProdutoRepository produtoRepository;

    // Função: Listar todas as movimentações registradas no banco de dados.
    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }

    // Função: Registrar uma nova movimentação de entrada ou saída de produto.
    @Transactional
    public Movimentacao registrarMovimentacao(Movimentacao movimentacao) {
        // Busca o produto no banco de dados e valida sua existência
        Produto produto = produtoRepository.findById(movimentacao.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        int quantidadeMovimentada = movimentacao.getQuantidadeMovimentada();
        int estoqueAtual = produto.getQuantidadeEmEstoque();

        // Aplica as regras de negócio conforme o tipo da movimentação
        if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
            // Verifica se há estoque suficiente para realizar a saída
            if (estoqueAtual < quantidadeMovimentada) {
                throw new RuntimeException("Estoque insuficiente para a saída!");
            }

            // Atualiza o estoque do produto
            produto.setQuantidadeEmEstoque(estoqueAtual - quantidadeMovimentada);

            // Alerta caso o estoque fique abaixo do mínimo definido
            if (produto.getQuantidadeEmEstoque() < produto.getQuantidadeMinima()) {
                System.out.println("ALERTA: Estoque do produto '" + produto.getNome() + "' está abaixo do mínimo!");
            }

        } else if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            // Incrementa o estoque em caso de entrada de produtos
            produto.setQuantidadeEmEstoque(estoqueAtual + quantidadeMovimentada);

            // Avisa caso o estoque ultrapasse o limite máximo
            if (produto.getQuantidadeEmEstoque() > produto.getQuantidadeMaxima()) {
                System.out.println("AVISO: Estoque do produto '" + produto.getNome() + "' ultrapassou o máximo recomendado!");
            }
        }

        // Atualiza o produto com o novo valor de estoque
        produtoRepository.save(produto);

        // Registra a movimentação e retorna o objeto salvo
        return movimentacaoRepository.save(movimentacao);
    }
}