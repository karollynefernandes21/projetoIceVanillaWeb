package com.dev.IceVanilla.service;

import com.dev.IceVanilla.dto.ItemVendaRequestDTO;
import com.dev.IceVanilla.dto.VendaFormDTO;
import com.dev.IceVanilla.model.Clientes;
import com.dev.IceVanilla.model.ItemVenda;
import com.dev.IceVanilla.model.Sorvetes;
import com.dev.IceVanilla.model.Vendas;
import com.dev.IceVanilla.repository.ClientesRepository;
import java.util.List;
import com.dev.IceVanilla.repository.VendasRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendasService {

    @Autowired
    private VendasRepository vendasRepository;

    @Autowired
    private ClientesRepository clientesRepository; 

    @Autowired
    private SorvetesService sorveteService; 

    // Salva a venda e os seus itens relacionados usando o DTO do formulário
    @Transactional
    public Vendas salvarNovaVenda(VendaFormDTO form) {
        
        // Cria o objeto de Vendas e busca o cliente
        Vendas novaVenda = new Vendas();
        
        Clientes cliente = clientesRepository.findById(form.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        novaVenda.setCliente(cliente);
        novaVenda.setDataHora(LocalDateTime.now());
        novaVenda.setFormaPagamento(form.getFormaPagamento());
        
        // Converte e associa os itens do DTO com a entidade de ItemVenda
        for (ItemVendaRequestDTO itemDto : form.getItens()) {
            
            // Busca o sorvete para garantir que o preço e o objeto de sorvetes estão corretos
            Sorvetes sorvete = sorveteService.buscarSorvetePorId(itemDto.getSorveteId());
            
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setSorvete(sorvete);
            itemVenda.setQuantidade(itemDto.getQuantidade());
            itemVenda.setPrecoUnitario(sorvete.getPreco()); 
            
            // Recalcula o subtotal e adiciona o item a venda
            itemVenda.calcularSubtotal(); 
            novaVenda.adicionarItem(itemVenda); 
        }
        
        // Recalcula o valor total final 
        novaVenda.calcularValorTotal();

        // Salva a venda 
        return vendasRepository.save(novaVenda);
    }

    // DELETE: Exclui uma venda pelo ID
    @Transactional
    public void excluirVenda(Long vendaId) {
        if (!vendasRepository.existsById(vendaId)) {
             throw new RuntimeException("Venda com ID " + vendaId + " não encontrada para exclusão.");
        }
        vendasRepository.deleteById(vendaId);
    }

    // GET: listando todas as vendas
    public List<Vendas> listarTodasVendas() {
        return vendasRepository.findAll();
    }
    
    // Buscando uma venda pelo id
     public Vendas buscarVendaPorId(Long id) {
         return vendasRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
     }
}
