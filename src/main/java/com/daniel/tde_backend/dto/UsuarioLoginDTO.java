package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDTO {

    @NotBlank(message = "Campo requerido")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Campo requerido")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String senha;

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsuarioLoginDTO(Usuario entity) {
        this.email = entity.getEmail();
        this.senha = entity.getSenha();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
