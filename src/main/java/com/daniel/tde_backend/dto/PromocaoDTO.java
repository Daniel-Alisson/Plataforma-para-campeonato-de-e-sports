package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Promocao;
import com.daniel.tde_backend.models.enums.PromocaoStatus;

import java.time.LocalDateTime;

public class PromocaoDTO {

    private String id;
    private String idJogador;
    private String nome;
    private LocalDateTime dataCriacao;
    private PromocaoStatus status;
    private String descricao;

    public PromocaoDTO() {
    }

    public PromocaoDTO(String id, String idJogador, String nome, LocalDateTime dataCriacao, PromocaoStatus status, String descricao) {
        this.id = id;
        this.idJogador = idJogador;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.descricao = descricao;
    }

    public PromocaoDTO(Promocao entity) {
        this.id = entity.getId();
        this.idJogador = entity.getIdJogador();
        this.nome = entity.getNome();
        this.dataCriacao = entity.getDataCriacao();
        this.status = entity.getStatus();
        this.descricao = entity.getDescricao();
    }

    public String getId() {
        return id;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public PromocaoStatus getStatus() {
        return status;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
