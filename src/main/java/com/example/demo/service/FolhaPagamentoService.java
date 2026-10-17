package com.example.demo.service;

import com.example.demo.dominio.Colaborador;
import com.example.demo.dominio.FolhaProcessada;
import com.example.demo.dto.RelatorioFolhaIndividual;
import com.example.demo.dto.RelatorioResumoFolha;
import com.example.demo.repositorio.ColaboradorRepositorio;
import com.example.demo.repositorio.FolhaProcessadaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolhaPagamentoService {
    
    @Autowired 
    private ColaboradorRepositorio repositorio;

    // Repositório do histórico da folha
    @Autowired
    private FolhaProcessadaRepositorio folhaRepositorio; 

    public Colaborador salvarColaborador(Colaborador colaborador) {
        return repositorio.save(colaborador);
    }

    public List<Colaborador> listarColaboradores() {
        return repositorio.findAll();
    }

    // ==========================================
    // MÉTODO NOVO: Necessário para o Controller não dar erro (Ponto 3 do Checklist)
    // ==========================================
    public void excluirColaborador(String matricula) {
        repositorio.deleteById(matricula);
    }

    // Exige o período e salva o histórico no banco de dados (Ponto 2 do Checklist)
    public List<RelatorioFolhaIndividual> gerarFolhaDePagamento(String periodoReferencia) {
        List<Colaborador> colaboradores = repositorio.findAll();

        // 1. Verifica se a folha desse mês já existe
        List<FolhaProcessada> folhaExistente = folhaRepositorio.findByPeriodoReferencia(periodoReferencia);
        
        // 2. Se a folha AINDA NÃO existir, fazemos a gravação ("Fotografia") no banco
        if (folhaExistente.isEmpty()) {
            for (Colaborador c : colaboradores) {
                FolhaProcessada registro = new FolhaProcessada();
                registro.setPeriodoReferencia(periodoReferencia);
                registro.setMatriculaColaborador(c.getMatricula());
                registro.setNomeColaborador(c.getNome());
                registro.setTipoColaborador(c.getClass().getSimpleName());
                registro.setSalarioFinal(c.calcularSalarioFinal());
                registro.setDataProcessamento(LocalDateTime.now());
                
                folhaRepositorio.save(registro);
            }
        }
        // Nota: Se já existir (!isEmpty), o código simplesmente ignora o 'save' e avança, 
        // em vez de dar o throw new RuntimeException que causava o Erro 500.

        // 3. Devolve a lista para o front-end
        return colaboradores.stream()
        .map(colab -> new RelatorioFolhaIndividual(
            colab.getMatricula(),
            colab.getNome(),
            colab.getClass().getSimpleName(),
            colab.getSalarioBase(),
            colab.calcularSalarioFinal() 
        )).collect(Collectors.toList()); 
    }

    // Recebe o período de referência para exibir no Resumo
    public RelatorioResumoFolha emitirResumoDaFolha(String periodoReferencia) {
        List<Colaborador> colaboradores = repositorio.findAll();

        BigDecimal totalFolha = colaboradores.stream()
        .map(Colaborador::calcularSalarioFinal)
        .reduce(BigDecimal.ZERO, BigDecimal::add); 

        // Cria o resumo e anexa a qual mês ele se refere
        RelatorioResumoFolha resumo = new RelatorioResumoFolha(colaboradores.size(), totalFolha);
        resumo.setPeriodoReferencia(periodoReferencia);
        return resumo;
    }
}