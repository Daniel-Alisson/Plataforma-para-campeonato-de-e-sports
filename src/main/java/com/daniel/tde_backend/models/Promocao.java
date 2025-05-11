package com.daniel.tde_backend.models;

import com.daniel.tde_backend.models.enums.PromocaoStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "solicitações")
public class Promocao {

    @Id
    private String id;
    private String idJogador;
    private String nome;
    private LocalDateTime dataCriacao;
    private PromocaoStatus status;

    public Promocao() {
    }

    public Promocao(String id, String idJogador, String nome, LocalDateTime dataCriacao, PromocaoStatus status) {
        this.id = id;
        this.idJogador = idJogador;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public PromocaoStatus getStatus() {
        return status;
    }

    public void setStatus(PromocaoStatus status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
