package br.com.controle_estoque.Controle_Estoque.service;

import br.com.controle_estoque.Controle_Estoque.dto.ListaPrecoDTO;
import br.com.controle_estoque.Controle_Estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ListaPrecoDTO> gerarListaDePrecos() {
        return produtoRepository.findListaDePrecos();
    }
}