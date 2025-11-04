package com.dev.IceVanilla.controller;

import com.dev.IceVanilla.dto.VendaFormDTO;
import com.dev.IceVanilla.model.Vendas;
import com.dev.IceVanilla.service.ClientesService;
import com.dev.IceVanilla.service.SorvetesService;
import com.dev.IceVanilla.service.VendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;

    @Autowired
    private SorvetesService sorvetesService;

    @Autowired
    private ClientesService clientesService;

    // GET: Exibe o formulário de nova venda
    @GetMapping("/nova")
    public String novaVenda(Model model) {

        model.addAttribute("vendaForm", new VendaFormDTO());

        // Carrega os dados para os selects
        model.addAttribute("sorvetes", sorvetesService.listarTodosSorvetes());
        model.addAttribute("clientes", clientesService.listarTodosClientes());

        return "cadastroVendas";
    }

    // POST: Finaliza e salva a venda no banco de dados
    @PostMapping("/salvar")
    public String salvarVenda(@ModelAttribute("vendaForm") VendaFormDTO vendaForm, RedirectAttributes ra) {

        try {
            vendasService.salvarNovaVenda(vendaForm);
            ra.addFlashAttribute("mensagemSucesso", "Venda realizada com sucesso!");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("mensagemErro", "Erro ao salvar a venda: " + e.getMessage());
            return "redirect:/vendas/nova";
        }

        // Redireciona para a página de listagem
        return "redirect:/vendas/lista";
    }

    // GET: Lista todas as vendas
    @GetMapping("/lista")
    public String listarVendas(Model model) {

        model.addAttribute("vendas", vendasService.listarTodasVendas());

        return "listaVendas";
    }

    // Mostra os detalhes da venda
    @GetMapping("/{id}")
    public String verDetalhesVenda(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        
        Vendas venda = vendasService.buscarVendaPorId(id);
        model.addAttribute("venda", venda);
        
        return "listaDetalhesVenda";
    }

    // GET: Exclui uma venda e redireciona
    @GetMapping("/excluir/{id}")
    public String excluirVenda(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            vendasService.excluirVenda(id);
            ra.addFlashAttribute("mensagemSucesso", "Venda excluída com sucesso!");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("mensagemErro", "Erro ao excluir a venda: " + e.getMessage());
        }

        return "redirect:/vendas/lista";
    }
}
