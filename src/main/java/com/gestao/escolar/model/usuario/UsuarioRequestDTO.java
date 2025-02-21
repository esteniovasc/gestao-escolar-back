package com.gestao.escolar.model.usuario;

public record UsuarioRequestDTO (String username, String email, String password, String role) {
}