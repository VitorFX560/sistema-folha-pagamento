package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RelatorioResumoFolha {
    
    private String periodoReferencia; // <-- A "gaveta" que faltava!
    private int totalColaboradores;
    private BigDecimal valorTotal;

    // Construtor que o seu Service está a usar
    public RelatorioResumoFolha(int totalColaboradores, BigDecimal valorTotal) {
        this.totalColaboradores = totalColaboradores;
        this.valorTotal = valorTotal;
    }
}