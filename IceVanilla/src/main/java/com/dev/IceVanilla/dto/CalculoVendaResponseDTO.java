
package com.dev.IceVanilla.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoVendaResponseDTO {
    
    private BigDecimal valorTotal;
    private List<ItemVendaResponseDTO> itens;
}
