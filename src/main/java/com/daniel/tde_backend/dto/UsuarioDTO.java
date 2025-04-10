package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.models.enums.UsuarioStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UsuarioDTO {

    private String id;
    @Size(min = 3, max = 50, message = "Nome precisa ter de 3 a 50 caracteres")
    @NotBlank(message = "Campo requerido")
    private String nome;
    private String nickName;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String imgUrl;
    private UsuarioStatus status;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nome, String nickName, String email, String senha, LocalDate dataNascimento, String imgUrl, UsuarioStatus status) {
        this.id = id;
        this.nome = nome;
        this.nickName = nickName;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public UsuarioDTO(Usuario entitiy) {
        this.id = entitiy.getId();
        this.nome = entitiy.getNome();
        this.nickName = entitiy.getNickName();
        this.email = entitiy.getEmail();
        this.senha = entitiy.getSenha();
        this.dataNascimento = entitiy.getDataNascimento();
        this.imgUrl = entitiy.getImgUrl();
        this.status = entitiy.getStatus();
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

    public UsuarioStatus getStatus() {
        return status;
    }
}
