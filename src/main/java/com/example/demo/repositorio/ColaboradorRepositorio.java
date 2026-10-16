package com.example.demo.repositorio;

import com.example.demo.dominio.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepositorio extends JpaRepository<Colaborador, String> { 
    // O detalhe crucial é o 'String' aqui em cima, para combinar com a matrícula!
}