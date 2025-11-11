package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.model.Categoria;
import br.com.controle_estoque.Controle_Estoque.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    // Repositório responsável pelo acesso e manipulação dos dados da categoria
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Função: Listar todas as categorias cadastradas no banco de dados.
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    // Função: Buscar uma categoria específica pelo seu ID.
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // Função: Salvar uma nova categoria no banco de dados.
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Função: Atualizar os dados de uma categoria existente.
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

    // Função: Excluir uma categoria do banco de dados pelo seu ID.
    public void deletarPorId(Long id) {
        categoriaRepository.deleteById(id);
    }
}