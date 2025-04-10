package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.models.enums.UsuarioTipo;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioDTO {

    private String id;
    @Size(min = 3, max = 50, message = "Nome precisa ter de 3 a 50 caracteres")
    @NotBlank(message = "Campo requerido")
    private String nome;
    @Size(min = 3, max = 50, message = "Nickname precisa ter de 3 a 50 caracteres")
    private String nickName;
    @NotBlank(message = "Campo requerido")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Campo requerido")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String senha;
    @NotNull(message = "Campo requerido")
    @Past(message = "Data precisa ser válida")
    private LocalDate dataNascimento;
    private String imgUrl;
    @NotNull(message = "Campo requerido")
    private UsuarioTipo tipo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nome, String nickName, String email, String senha, LocalDate dataNascimento, String imgUrl, UsuarioTipo tipo) {
        this.id = id;
        this.nome = nome;
        this.nickName = nickName;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.imgUrl = imgUrl;
        this.tipo = tipo;
    }

    public UsuarioDTO(Usuario entitiy) {
        this.id = entitiy.getId();
        this.nome = entitiy.getNome();
        this.nickName = entitiy.getNickName();
        this.email = entitiy.getEmail();
        this.senha = entitiy.getSenha();
        this.dataNascimento = entitiy.getDataNascimento();
        this.imgUrl = entitiy.getImgUrl();
        this.tipo = entitiy.getTipo();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public UsuarioTipo getTipo() {
        return tipo;
    }
}
