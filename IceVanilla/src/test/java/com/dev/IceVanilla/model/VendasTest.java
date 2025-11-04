package com.dev.IceVanilla.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class VendasTest {

    public VendasTest() {
    }

    // --- Calcula o valor total para itens com valores positivos ---
    @Test
    void calcularValorTotalComItens() {

        // Cria os itens da venda com subtotais
        ItemVenda item1 = new ItemVenda();
        item1.setSubtotal(new BigDecimal("10.50"));

        ItemVenda item2 = new ItemVenda();
        item2.setSubtotal(new BigDecimal("20.00"));

        List<ItemVenda> listaItens = Arrays.asList(item1, item2);

        // Cria a instância da Venda e injeta os itens
        Vendas venda = new Vendas();
        venda.setItens(listaItens);

        // Valor esperado: 10.50 + 20.00 = 30.50
        BigDecimal esperado = new BigDecimal("30.50");

        // Chama o método a ser testado
        venda.calcularValorTotal();

        // Verifica se o valor total foi atualizado corretamente
        assertEquals(esperado, venda.getValorTotal());
    }

    // --- Calcula o valor total para uma lista de itens vazia ---
    @Test
    void calcularValorTotalSemItens() {

        Vendas venda = new Vendas();
        venda.setItens(Collections.emptyList()); // Lista de itens vazia

        BigDecimal esperado = BigDecimal.ZERO;

        venda.calcularValorTotal();

        assertEquals(esperado, venda.getValorTotal());
    }

}
