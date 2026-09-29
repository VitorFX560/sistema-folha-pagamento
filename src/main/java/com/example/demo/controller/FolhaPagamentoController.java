
package com.example.demo.controller;

import com.example.demo.dominio.Colaborador;
import com.example.demo.dto.RelatorioFolhaIndividual;
import com.example.demo.dto.RelatorioResumoFolha;
import com.example.demo.service.FolhaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/folha")
public class FolhaPagamentoController {

    @Autowired
    private FolhaPagamentoService service;

    //Recebe os dados em JSON e salva no banco de dados
    @PostMapping("/colaboradores")
    public ResponseEntity<Colaborador> cadastrar(@RequestBody Colaborador colaborador) {
        Colaborador salvo = service.salvarColaborador(colaborador);
        return ResponseEntity.ok(salvo);
    }

     //Retorna a lista de todos os colaboradores cadastrados
    @GetMapping("/colaboradores")
    public ResponseEntity<List<Colaborador>> listarTodos() {
        return ResponseEntity.ok(service.listarColaboradores());
    }

    // UC03: Retorna a folha de pagamento detalhada com os salários finais calculados
    @GetMapping("/detalhada")
    public ResponseEntity<List<RelatorioFolhaIndividual>> gerarFolhaDetalhada() {
        return ResponseEntity.ok(service.gerarFolhaDePagamento());
    }

    // Retorna o resumo consolidado com o total da folha
    @GetMapping("/resumo")
    public ResponseEntity<RelatorioResumoFolha> emitirResumo() {
        return ResponseEntity.ok(service.emitirResumoDaFolha());
    }
}