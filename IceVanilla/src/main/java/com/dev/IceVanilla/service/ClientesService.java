package com.dev.IceVanilla.service;

import com.dev.IceVanilla.model.Clientes;
import com.dev.IceVanilla.repository.ClientesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clienteRepository;

    // POST: cadastrando um novo cliente
    public Clientes cadastrarCliente(Clientes cliente) {
        
        // Garantia de que o id está nulo
        cliente.setId(null);
        
        return clienteRepository.save(cliente);
    }

    // Buscando um cliente pelo id
    public Clientes buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    // PUT: atualizando um cliente
    public Clientes atualizarCliente(Long clienteId, Clientes clienteAtualizado) {
        // Busca o cliente existente
        Clientes cliente = buscarClientePorId(clienteId);

        // Atualiza os campos
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setTelefone(clienteAtualizado.getTelefone());

        // Salva a versão atualizada
        return clienteRepository.save(cliente);
    }

    // GET: listando todos os clientes
    public List<Clientes> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    // DELETE: excluindo um cliente
    public void excluirCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
