package com.daniel.tde_backend.models.enums;

public enum UsuarioTipo {

    JOGADOR("jogador"), PROMOTOR("promotor"), ADMIN("admin");

    private String role;

    UsuarioTipo(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}