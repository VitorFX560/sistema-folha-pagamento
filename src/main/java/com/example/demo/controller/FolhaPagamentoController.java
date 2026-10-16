package com.example.demo.controller;

import com.example.demo.dominio.Colaborador;
import com.example.demo.dto.RelatorioFolhaIndividual;
import com.example.demo.dto.RelatorioResumoFolha;
import com.example.demo.service.FolhaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folha")
@CrossOrigin(origins = "*") 
public class FolhaPagamentoController {

    @Autowired
    private FolhaPagamentoService service;

    

    

    @PostMapping("/colaboradores")
    public ResponseEntity<Colaborador> salvarColaborador(@RequestBody Colaborador colaborador) {
        return ResponseEntity.ok(service.salvarColaborador(colaborador));
    }

    @GetMapping("/colaboradores")
    public ResponseEntity<List<Colaborador>> listarColaboradores() {
        return ResponseEntity.ok(service.listarColaboradores());
    }

    //  Rota para Atualizar os dados de um colaborador (PUT)
    @PutMapping("/colaboradores/{matricula}")
    public ResponseEntity<Colaborador> atualizarColaborador(@PathVariable String matricula, @RequestBody Colaborador colaboradorAtualizado) {
        
        
        return ResponseEntity.ok(service.salvarColaborador(colaboradorAtualizado));
    }

    // Rota para Excluir um colaborador (DELETE)
    @DeleteMapping("/colaboradores/{matricula}")
    public ResponseEntity<Void> excluirColaborador(@PathVariable String matricula) {
        service.excluirColaborador(matricula);
        return ResponseEntity.noContent().build();
    }

    
    // PROCESSAMENTO DA FOLHA COM PERÍODO
    

    // NOVO: Agora exige que o React envie o período (ex: /api/folha/detalhada?periodo=10/2026)
    @GetMapping("/detalhada")
    public ResponseEntity<List<RelatorioFolhaIndividual>> gerarFolha(
            @RequestParam(name = "periodo") String periodoReferencia) {
        return ResponseEntity.ok(service.gerarFolhaDePagamento(periodoReferencia));
    }

    // NOVO: Exige o período para emitir o resumo
    @GetMapping("/resumo")
    public ResponseEntity<RelatorioResumoFolha> emitirResumo(
            @RequestParam(name = "periodo") String periodoReferencia) {
        return ResponseEntity.ok(service.emitirResumoDaFolha(periodoReferencia));
    }
}