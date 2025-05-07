package com.daniel.tde_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "equipe")
public class Equipe {

    @Id
    private String id;
    private String nome;
    private List<String> membros;
    private LocalDateTime dataCriacao;
    private String logoUrl;

    public Equipe() {
    }

    public Equipe(String id, String nome, List<String> membros, LocalDateTime dataCriacao, String logoUrl) {
        this.id = id;
        this.nome = nome;
        this.membros = membros;
        this.dataCriacao = dataCriacao;
        this.logoUrl = logoUrl;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<String> getMembros() {
        return membros;
    }

    public void setMembros(List<String> membros) {
        this.membros = membros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
