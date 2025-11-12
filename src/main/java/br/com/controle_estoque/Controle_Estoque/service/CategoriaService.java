package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.model.Categoria;
import br.com.controle_estoque.Controle_Estoque.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócios e operações CRUD
 * relacionadas à entidade {@link Categoria}.
 */
@Service
public class CategoriaService {

    /**
     * Repositório responsável pelo acesso e manipulação dos dados da categoria.
     */
    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Função: Listar todas as categorias cadastradas no banco de dados.
     *
     * @return Uma lista ({@code List<Categoria>}) de todas as categorias.
     */
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    /**
     * Função: Buscar uma categoria específica pelo seu ID.
     *
     * @param id O ID da categoria a ser buscada.
     * @return Um {@link Optional} contendo a categoria se encontrada, ou {@link Optional#empty()} caso contrário.
     */
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    /**
     * Função: Salvar uma nova categoria no banco de dados.
     *
     * @param categoria O objeto {@link Categoria} a ser salvo.
     * @return A categoria salva (com o ID preenchido pela persistência).
     */
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    /**
     * Função: Atualizar os dados de uma categoria existente.
     *
     * @param id O ID da categoria a ser atualizada.
     * @param categoriaDetalhes Um objeto {@link Categoria} contendo os novos dados.
     * @return A categoria atualizada, ou {@code null} se a categoria não foi encontrada.
     */
    public Categoria atualizar(Long id, Categoria categoriaDetalhes) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoriaExistente = categoriaOptional.get();

            // Atualiza os campos da categoria
            categoriaExistente.setNome(categoriaDetalhes.getNome());
            categoriaExistente.setTamanho(categoriaDetalhes.getTamanho());
            categoriaExistente.setEmbalagem(categoriaDetalhes.getEmbalagem());

            // Salva e retorna a categoria atualizada
            return categoriaRepository.save(categoriaExistente);
        } else {
            return null;
        }
    }

    /**
     * Função: Excluir uma categoria do banco de dados pelo seu ID.
     *
     * @param id O ID da categoria a ser excluída.
     */
    public void deletarPorId(Long id) {
        categoriaRepository.deleteById(id);
    }
}