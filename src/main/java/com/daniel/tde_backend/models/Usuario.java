package com.daniel.tde_backend.models;

import com.daniel.tde_backend.models.enums.UsuarioTipo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Document(collection = "usuarios")
public class Usuario implements UserDetails {

    // EU USAVA LONG, MAS PARECE Q NO MONGODB ELE USA _ID EM VEZ DE ID, ENTÃO COLOQUEI STRING
    @Id
    private String id;
    private String nome;
    private String nickName;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String imgUrl;
    private UsuarioTipo tipo;

    public Usuario() {
    }

    public Usuario(String id, String nome, String nickName, String email, String senha, LocalDate dataNascimento, String imgUrl, UsuarioTipo tipo) {
        this.id = id;
        this.nome = nome;
        this.nickName = nickName;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.imgUrl = imgUrl;
        this.tipo = tipo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioTipo getTipo() {
        return tipo;
    }

    public void setTipo(UsuarioTipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipo == UsuarioTipo.PROMOTOR) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_PROMOTOR"),
                    new SimpleGrantedAuthority("ROLE_JOGADOR"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_JOGADOR"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
