package com.example.demo.repositorio;

import com.example.demo.dominio.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepositorio extends JpaRepository<Colaborador, Long> {

}

