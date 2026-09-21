
package com.example.demo.dto;

import java.math.BigDecimal;

public record RelatorioFolhaIndividual(
    Long matricula,
    String nome,
    String tipoColaborador,
    BigDecimal salarioBase,
    BigDecimal salarioFinal
) {}