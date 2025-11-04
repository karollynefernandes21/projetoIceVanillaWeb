
package com.dev.IceVanilla.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaFormDTO {
    
    private Long clienteId;
    private String formaPagamento;
    private BigDecimal valorTotal;

    private List<ItemVendaRequestDTO> itens = new ArrayList<>();
}
