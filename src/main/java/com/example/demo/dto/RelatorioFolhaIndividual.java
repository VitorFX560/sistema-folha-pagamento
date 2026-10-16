package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RelatorioFolhaIndividual {
    
    private String matricula;
    private String nome;
    private String tipoColaborador;
    private BigDecimal salarioBase;
    private BigDecimal salarioFinal;
    
    // Construtor vazio (Faz o papel do @NoArgsConstructor de forma nativa)
    public RelatorioFolhaIndividual() {
    }

    // Construtor completo (Faz o papel do @AllArgsConstructor de forma nativa)
    public RelatorioFolhaIndividual(String matricula, String nome, String tipoColaborador, BigDecimal salarioBase, BigDecimal salarioFinal) {
        this.matricula = matricula;
        this.nome = nome;
        this.tipoColaborador = tipoColaborador;
        this.salarioBase = salarioBase;
        this.salarioFinal = salarioFinal;
    }
}