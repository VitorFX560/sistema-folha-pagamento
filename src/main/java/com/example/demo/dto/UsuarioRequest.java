package com.example.demo.dto;

import com.example.demo.dominio.Role;

public record UsuarioRequest(String nome, String email, String senha, Role role) {
}