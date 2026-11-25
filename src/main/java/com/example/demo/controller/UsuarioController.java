package com.example.demo.controller;

import com.example.demo.dominio.Usuario;
import com.example.demo.dto.UsuarioRequest;
import com.example.demo.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioRequest dados) {
        if (usuarioRepositorio.existsByEmail(dados.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um usuário cadastrado com este e-mail.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dados.nome());
        novoUsuario.setEmail(dados.email());
        novoUsuario.setSenha(passwordEncoder.encode(dados.senha()));
        novoUsuario.setRole(dados.role());

        usuarioRepositorio.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuário " + novoUsuario.getNome() + " (" + novoUsuario.getRole() + ") criado com sucesso!");
    }
}