
package com.dev.IceVanilla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sorvetes")
public class Sorvetes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="O sabor é obrigatório.")
    private String sabor;
    
    @NotBlank(message="A descrição é obrigatória.")
    private String descricao;
    
    @NotNull(message="O preço é obrigatório.")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
}
