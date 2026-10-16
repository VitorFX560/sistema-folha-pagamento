package com.example.demo.repositorio;

import com.example.demo.dominio.FolhaProcessada;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FolhaProcessadaRepositorio extends JpaRepository<FolhaProcessada, Long> {
    // Permite buscar o histórico de um mês específico
    List<FolhaProcessada> findByPeriodoReferencia(String periodoReferencia);
}