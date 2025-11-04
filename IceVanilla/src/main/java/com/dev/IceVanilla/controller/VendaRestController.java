
package com.dev.IceVanilla.controller;

import com.dev.IceVanilla.dto.CalculoVendaResponseDTO;
import com.dev.IceVanilla.dto.ItemVendaRequestDTO;
import com.dev.IceVanilla.dto.ItemVendaResponseDTO;
import com.dev.IceVanilla.model.Sorvetes;
import com.dev.IceVanilla.service.SorvetesService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendas")
public class VendaRestController {

    @Autowired
    private SorvetesService sorvetesService; 

    
    // Endpoint chamado via AJAX para calcular o valor total da venda dinamicamente
    @PostMapping("/calcular-totais")
    public ResponseEntity<CalculoVendaResponseDTO> calcularTotais(@RequestBody List<ItemVendaRequestDTO> itens) {

        BigDecimal totalGeral = BigDecimal.ZERO;
        List<ItemVendaResponseDTO> itensCalculados = new ArrayList<>();

        for (ItemVendaRequestDTO itemRequest : itens) {
            
            // Busca o sorvete no banco de dados para pegar o preço real
            Sorvetes sorvete = sorvetesService.buscarSorvetePorId(itemRequest.getSorveteId());

            // Calcula o subtotal
            BigDecimal precoUnitario = sorvete.getPreco();
            BigDecimal quantidade = new BigDecimal(itemRequest.getQuantidade());
            BigDecimal subtotal = precoUnitario.multiply(quantidade);
            
            // Acumula no total geral
            totalGeral = totalGeral.add(subtotal);

            // Preenche o DTO de resposta do item
            ItemVendaResponseDTO itemResponse = new ItemVendaResponseDTO();
            itemResponse.setSorveteId(sorvete.getId());
            itemResponse.setNomeSorvete(sorvete.getSabor()); 
            itemResponse.setQuantidade(itemRequest.getQuantidade());
            itemResponse.setPrecoUnitario(precoUnitario);
            itemResponse.setSubtotal(subtotal);
            
            itensCalculados.add(itemResponse);
        }

        // Preenche o DTO de resposta principal
        CalculoVendaResponseDTO response = new CalculoVendaResponseDTO(totalGeral, itensCalculados);

        return ResponseEntity.ok(response);
    }
}
