
package com.example.demo.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_colaborador", discriminatorType = DiscriminatorType.STRING)
public abstract class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;

    @NotBlank(message = "O nome do colaborador é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O salário base é obrigatório.")
    @PositiveOrZero 
    @Column(nullable = false)
    private BigDecimal salarioBase;

    public abstract BigDecimal calcularSalarioFinal();
}