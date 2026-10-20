package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RelatorioResumoFolha {
    
    private String periodoReferencia; 
    private int totalColaboradores;
    private BigDecimal valorTotal;

   
    public RelatorioResumoFolha(int totalColaboradores, BigDecimal valorTotal) {
        this.totalColaboradores = totalColaboradores;
        this.valorTotal = valorTotal;
    }
}