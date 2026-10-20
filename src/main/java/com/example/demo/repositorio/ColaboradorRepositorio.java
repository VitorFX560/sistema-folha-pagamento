package com.example.demo.repositorio;

import com.example.demo.dominio.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepositorio extends JpaRepository<Colaborador, String> { 
    
}