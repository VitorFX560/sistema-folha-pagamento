package com.example.demo.dominio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@DiscriminatorValue("COMISSIONADO")
public class ColaboradorComissionado extends Colaborador {

    @PositiveOrZero
    private BigDecimal valorVendas = BigDecimal.ZERO;

    @PositiveOrZero
    private BigDecimal percentualComissao = BigDecimal.ZERO;

    // GETTERS E SETTERS MANUAIS
    public BigDecimal getValorVendas() {
        return valorVendas;
    }

    public void setValorVendas(BigDecimal valorVendas) {
        this.valorVendas = valorVendas;
    }

    public BigDecimal getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(BigDecimal percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    @Override
    public BigDecimal calcularSalarioFinal() {
        BigDecimal fatorComissao = percentualComissao.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        BigDecimal valorComissao = valorVendas.multiply(fatorComissao);
        return getSalarioBase().add(valorComissao).setScale(2, RoundingMode.HALF_UP);
    }
}