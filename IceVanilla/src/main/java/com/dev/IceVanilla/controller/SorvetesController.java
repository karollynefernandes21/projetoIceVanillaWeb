
package com.dev.IceVanilla.controller;

import com.dev.IceVanilla.model.Sorvetes;
import com.dev.IceVanilla.service.SorvetesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/sorvetes")
public class SorvetesController {

    @Autowired
    private SorvetesService sorveteService;

    // --- Operações CRUD de Sorvete ---
    
    // Exibe form para cadastro de sorvetes
    @GetMapping("/novo")
    public String novoSorvete(Model model) {
        model.addAttribute("sorvete", new Sorvetes());
        return "cadastroSorvetes";
    }

    // Salva um novo sorvete (ou atualiza)
    @PostMapping
    public String salvarSorvete(@ModelAttribute Sorvetes sorvete) {
        sorveteService.cadastrarSorvete(sorvete); 
        return "redirect:/sorvetes"; // Redireciona para a lista
    }

    // Lista todos os sorvetes
    @GetMapping
    public String listarSorvetes(Model model) {
        List<Sorvetes> sorvetes = sorveteService.listarTodosSorvetes();
        model.addAttribute("sorvetes", sorvetes); 
        return "listaSorvetes";
    }

    // Exibe o form de edição
    @GetMapping("/{id}/editar")
    public String editarSorvete(@PathVariable Long id, Model model) {
        Sorvetes sorvete = sorveteService.buscarSorvetePorId(id);
        model.addAttribute("sorvete", sorvete);
        return "cadastroSorvetes";
    }
    
    // Atualiza os dados do sorvete
    @PostMapping("/{id}/atualizar")
    public String atualizarSorvete(@PathVariable Long id, @ModelAttribute Sorvetes sorveteAtualizado) {
        sorveteService.atualizarSorvete(id, sorveteAtualizado); 
        return "redirect:/sorvetes";
    }
    
    // Exclui um sorvete
    @GetMapping("/{id}/excluir")
    public String excluirSorvete(@PathVariable Long id) {
        sorveteService.excluirSorvete(id);
        return "redirect:/sorvetes";
    }
}
