package com.example.demo.service;

import com.example.demo.dominio.Colaborador;
import com.example.demo.dto.RelatorioFolhaIndividual;
import com.example.demo.dto.RelatorioResumoFolha;
import com.example.demo.repositorio.ColaboradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolhaPagamentoService {
    
    // 1. Declaramos o repositório corretamente aqui em cima
    @Autowired 
    private ColaboradorRepositorio repositorio;

    // 2. Método agora é public e usa 'repositorio'
    public Colaborador salvarColaborador(Colaborador colaborador) {
        return repositorio.save(colaborador);
    }

    // Lista todos os colaboradores
    public List<Colaborador> listarColaboradores() {
        return repositorio.findAll();
    }

    // Gera Folha Detalhada
    public List<RelatorioFolhaIndividual> gerarFolhaDePagamento() {
        return repositorio.findAll().stream()
        .map(colab -> new RelatorioFolhaIndividual(
            colab.getMatricula(),
            colab.getNome(),
            colab.getClass().getSimpleName(),
            colab.getSalarioBase(),
            colab.calcularSalarioFinal() // Aqui o Polimorfismo calcula automaticamente a regra correta
        )).collect(Collectors.toList()); // 3. 'C' maiúsculo em Collectors
    }

    // Emite Resumo Consolidado
    public RelatorioResumoFolha emitirResumoDaFolha() {
        List<Colaborador> colaboradores = repositorio.findAll();

        BigDecimal totalFolha = colaboradores.stream()
        .map(Colaborador::calcularSalarioFinal)
        .reduce(BigDecimal.ZERO, BigDecimal::add); // 4. ZERO tudo em maiúsculo

        return new RelatorioResumoFolha(colaboradores.size(), totalFolha);
    }
}