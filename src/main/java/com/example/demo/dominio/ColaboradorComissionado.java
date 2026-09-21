package com.example.demo.dominio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity 
@DiscriminatorValue("COMISSIONADO")
public class ColaboradorComissionado extends Colaborador {

    @PositiveOrZero 
    private BigDecimal valorVendas = BigDecimal.ZERO;

    @PositiveOrZero 
    private BigDecimal percentualComissao = BigDecimal.ZERO;

    @Override
    public BigDecimal calcularSalarioFinal() {
        BigDecimal fatorComissao = percentualComissao.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        BigDecimal valorComissao = valorVendas.multiply(fatorComissao);
        return getSalarioBase().add(valorComissao).setScale(2, RoundingMode.HALF_UP);
    }
}