
package com.dev.IceVanilla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaRequestDTO {
    
    private Long sorveteId;
    private Integer quantidade;
}
