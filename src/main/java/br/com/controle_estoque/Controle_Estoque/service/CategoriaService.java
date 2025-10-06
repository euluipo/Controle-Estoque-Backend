package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.model.Categoria;
import br.com.controle_estoque.Controle_Estoque.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoriaDetalhes) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoriaExistente = categoriaOptional.get();
            categoriaExistente.setNome(categoriaDetalhes.getNome());
            categoriaExistente.setTamanho(categoriaDetalhes.getTamanho());
            categoriaExistente.setEmbalagem(categoriaDetalhes.getEmbalagem());
            return categoriaRepository.save(categoriaExistente);
        } else {
            return null;
        }
    }

    public void deletarPorId(Long id) {
        categoriaRepository.deleteById(id);
    }
}