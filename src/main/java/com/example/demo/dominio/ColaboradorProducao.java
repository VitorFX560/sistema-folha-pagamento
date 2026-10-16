package com.example.demo.dominio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@DiscriminatorValue("PRODUCAO")
public class ColaboradorProducao extends Colaborador {

    @PositiveOrZero
    private Integer quantidadeProduzida = 0;

    @PositiveOrZero
    private BigDecimal valorPorUnidade = BigDecimal.ZERO;

    // GETTERS E SETTERS MANUAIS
    public Integer getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(Integer quantidadeProduzida) {
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public BigDecimal getValorPorUnidade() {
        return valorPorUnidade;
    }

    public void setValorPorUnidade(BigDecimal valorPorUnidade) {
        this.valorPorUnidade = valorPorUnidade;
    }

    @Override
    public BigDecimal calcularSalarioFinal() {
        BigDecimal produtividade = BigDecimal.valueOf(quantidadeProduzida).multiply(valorPorUnidade);
        return getSalarioBase().add(produtividade).setScale(2, RoundingMode.HALF_UP);
    }
}