package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Equipe;

import java.time.LocalDateTime;
import java.util.List;

public class EquipeDTO {

    String id;
    private String nome;
    private List<String> membros;
    private LocalDateTime dataCriacao;
    private String logoUrl;

    public EquipeDTO() {
    }

    public EquipeDTO(String id, String nome, List<String> membros, LocalDateTime dataCriacao, String logoUrl) {
        this.id = id;
        this.nome = nome;
        this.membros = membros;
        this.dataCriacao = dataCriacao;
        this.logoUrl = logoUrl;
    }

    public EquipeDTO(Equipe entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.membros = entity.getMembros();
        this.dataCriacao = entity.getDataCriacao();
        this.logoUrl = entity.getLogoUrl();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getId() {
        return id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public List<String> getMembros() {
        return membros;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "EquipeDTO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", membros=" + membros +
                ", dataCriacao=" + dataCriacao +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
