
package com.dev.IceVanilla.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vendas")
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;

    @Column(nullable = false, name = "data_hora")
    private LocalDateTime dataHora;

    @Column(nullable = false, precision = 10, scale = 2, name = "valor_total")
    private BigDecimal valorTotal;
    
    @Column(name = "forma_pagamento", length = 50)
    private String formaPagamento;

    // Relacionamento com ItemVenda (One-to-Many)
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();
    
    // Método para adicionar itens a lista de itens
    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        item.setVenda(this);
    }
    
    // Método para calcular o valor total
    public void calcularValorTotal() {
        this.valorTotal = this.itens.stream()
            .map(ItemVenda::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
