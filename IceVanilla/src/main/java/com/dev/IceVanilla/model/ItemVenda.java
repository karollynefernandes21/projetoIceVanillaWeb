
package com.dev.IceVanilla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item_venda")
public class ItemVenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Vendas (venda_id)
    @ManyToOne 
    @JoinColumn(name = "venda_id", nullable = false)
    private Vendas venda;

    // Relacionamento com Sorvetes (sorvete_id)
    @ManyToOne 
    @JoinColumn(name = "sorvete_id", nullable = false)
    private Sorvetes sorvete;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 10, scale = 2, name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(nullable = false, precision = 10, scale = 2, name = "subtotal")
    private BigDecimal subtotal;

    // Método para calcular o subtotal
    public void calcularSubtotal() {
        if (precoUnitario != null && quantidade != null) {
            this.subtotal = precoUnitario.multiply(new BigDecimal(quantidade));
        }
    }
}
