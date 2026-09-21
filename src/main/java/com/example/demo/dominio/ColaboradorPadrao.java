package com.example.demo.dominio;

    import jakarta.persistence.DiscriminatorValue;
    import jakarta.persistence.Entity;
    import java.math.BigDecimal;

    @Entity
    @DiscriminatorValue("PADRAO")
public class ColaboradorPadrao extends Colaborador {

    @Override
    public BigDecimal calcularSalarioFinal() {
        return getSalarioBase();
    }
}
