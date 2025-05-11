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
    @NotNull(message = "Campo requerido")
    @Past(message = "Data precisa ser válida")
    private LocalDate dataNascimento;
    private String imgUrl;
    private UsuarioTipo tipo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nome, String nickName, LocalDate dataNascimento, String imgUrl, UsuarioTipo tipo) {
        this.id = id;
        this.nome = nome;
        this.nickName = nickName;
        this.dataNascimento = dataNascimento;
        this.imgUrl = imgUrl;
        this.tipo = tipo;
    }

    public UsuarioDTO(Usuario entitiy) {
        this.id = entitiy.getId();
        this.nome = entitiy.getNome();
        this.nickName = entitiy.getNickName();
        this.dataNascimento = entitiy.getDataNascimento();
        this.imgUrl = entitiy.getImgUrl();
        this.tipo = entitiy.getTipo();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
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

    public UsuarioTipo getTipo() {
        return tipo;
    }
}
