package com.dev.IceVanilla.service;

import com.dev.IceVanilla.model.Sorvetes;
import com.dev.IceVanilla.repository.SorvetesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SorvetesService {

    @Autowired
    private SorvetesRepository sorveteRepository;

    // POST: cadastrando um novo sorvete
    public Sorvetes cadastrarSorvete(Sorvetes sorvete) {

        // Garantia de que o id está nulo
        sorvete.setId(null);

        return sorveteRepository.save(sorvete);
    }

    // Buscando um sorvete pelo id
    public Sorvetes buscarSorvetePorId(Long id) {
        return sorveteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sorvete não encontrado"));
    }

    // PUT: atualizando um sorvete
    public Sorvetes atualizarSorvete(Long sorveteId, Sorvetes sorveteAtualizado) {

        // Busca o sorvete existente
        Sorvetes sorvete = buscarSorvetePorId(sorveteId);

        // Atualiza os campos
        sorvete.setSabor(sorveteAtualizado.getSabor());
        sorvete.setDescricao(sorveteAtualizado.getDescricao());
        sorvete.setPreco(sorveteAtualizado.getPreco());

        // Salva a versão atualizada
        return sorveteRepository.save(sorvete);
    }

    // GET: listando todos os sorvetes
    public List<Sorvetes> listarTodosSorvetes() {
        return sorveteRepository.findAll();
    }

    // DELETE: excluindo um sorvete
    public void excluirSorvete(Long sorveteId) {
        sorveteRepository.deleteById(sorveteId);
    }
}
