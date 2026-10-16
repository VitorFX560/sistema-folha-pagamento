package com.example.demo.dominio;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// ---------------------------------------------------------
// Ensina o Java a converter o JSON na classe correta
// ---------------------------------------------------------
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ColaboradorPadrao.class, name = "Padrao"),
    @JsonSubTypes.Type(value = ColaboradorComissionado.class, name = "Comissionado"),
    @JsonSubTypes.Type(value = ColaboradorProducao.class, name = "Producao")
})
public abstract class Colaborador {

    @Id
    @Column(unique = true, nullable = false)
    private String matricula;

    @Column(nullable = false)
    private String nome;

    private BigDecimal salarioBase;

    // ==========================================
    // GETTERS E SETTERS MANUAIS
    // ==========================================

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    // ==========================================
    // MÉTODO ABSTRATO DE CÁLCULO
    // ==========================================
    public abstract BigDecimal calcularSalarioFinal();
}