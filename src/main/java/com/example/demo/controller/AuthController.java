package com.example.demo.controller;

import com.example.demo.dominio.Usuario;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = authenticationManager.authenticate(authToken);

        Usuario usuario = (Usuario) auth.getPrincipal();
        String token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponse(
                token,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        ));
    }
}