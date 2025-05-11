package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioCadastroDTO {

    @NotBlank(message = "Campo requerido")
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank(message = "Campo requerido")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Senha deve conter pelo menos 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial")
    private String senha;

    public UsuarioCadastroDTO() {
    }

    public UsuarioCadastroDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsuarioCadastroDTO(Usuario entity) {
        this.email = entity.getEmail();
        this.senha = entity.getSenha();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
