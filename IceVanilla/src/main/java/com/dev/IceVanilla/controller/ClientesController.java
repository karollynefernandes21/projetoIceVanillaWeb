
package com.dev.IceVanilla.controller;

import com.dev.IceVanilla.model.Clientes;
import com.dev.IceVanilla.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clienteService;

    // --- Operações CRUD de Cliente ---
    
    // Exibe form para cadastro de clientes
    @GetMapping("/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Clientes());
        return "cadastroClientes"; 
    }

    // Salva um novo cliente (ou atualiza, dependendo se o objeto tem ID)
    @PostMapping
    public String salvarCliente(@ModelAttribute Clientes cliente) {
        clienteService.cadastrarCliente(cliente); 
        return "redirect:/clientes"; // Redireciona para a lista
    }

    // Lista todos os clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodosClientes());
        return "listaClientes"; 
    }

    // Exibe o form de edição
    @GetMapping("/{id}/editar")
    public String editarCliente(@PathVariable Long id, Model model) {
        Clientes cliente = clienteService.buscarClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "cadastroClientes"; 
    }
    
    // Atualiza os dados do cliente
    @PostMapping("/{id}/atualizar")
    public String atualizarCliente(@PathVariable Long id, @ModelAttribute Clientes clienteAtualizado) {
        clienteService.atualizarCliente(id, clienteAtualizado); 
        return "redirect:/clientes";
    }
    
    // Exclui um cliente
    @GetMapping("/{id}/excluir")
    public String excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return "redirect:/clientes";
    }
}
