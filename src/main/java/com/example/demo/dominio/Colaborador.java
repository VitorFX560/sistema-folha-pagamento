
package com.example.demo.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_colaborador", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo_colaborador")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ColaboradorComissionado.class, name = "COMISSIONADO"),
    @JsonSubTypes.Type(value = ColaboradorPadrao.class, name = "PADRAO"),
    @JsonSubTypes.Type(value = ColaboradorProducao.class, name = "PRODUCAO")
})
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