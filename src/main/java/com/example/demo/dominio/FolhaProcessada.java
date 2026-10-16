package com.example.demo.dominio;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class FolhaProcessada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String periodoReferencia;

    @Column(nullable = false)
    private String matriculaColaborador; // <-- O atributo que você identificou que faltava!

    @Column(nullable = false)
    private String nomeColaborador;

    @Column(nullable = false)
    private String tipoColaborador;

    @Column(nullable = false)
    private BigDecimal salarioFinal;

    private LocalDateTime dataProcessamento = LocalDateTime.now();
}