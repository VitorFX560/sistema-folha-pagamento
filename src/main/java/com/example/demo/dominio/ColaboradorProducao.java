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
@DiscriminatorValue("PRODUCAO")
public class ColaboradorProducao extends Colaborador {

    @PositiveOrZero
    private Integer quantidadeProduzida = 0;

    @PositiveOrZero
    private BigDecimal valorPorUnidade = BigDecimal.ZERO;

    @Override
    public BigDecimal calcularSalarioFinal() {
        BigDecimal produtividade = BigDecimal.valueOf(quantidadeProduzida).multiply(valorPorUnidade);
        return getSalarioBase().add(produtividade).setScale(2, RoundingMode.HALF_UP);
    }
}