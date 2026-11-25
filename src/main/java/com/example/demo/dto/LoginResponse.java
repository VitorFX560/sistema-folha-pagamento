package com.example.demo.dto;

import com.example.demo.dominio.Role;

public record LoginResponse(String token, String nome, String email, Role role) {
}