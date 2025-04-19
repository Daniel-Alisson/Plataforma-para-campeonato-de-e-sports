package com.daniel.tde_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "inscricoes")
public class Inscricao {

    @Id
    private String id;
    private String campeonatoId;
    private String usuarioId;
    private String time;
    private LocalDateTime dataInscricao;

    public Inscricao() {
        this.dataInscricao = LocalDateTime.now();
    }

    public Inscricao(String id, String campeonatoId, String usuarioId, String time) {
        this.id = id;
        this.campeonatoId = campeonatoId;
        this.usuarioId = usuarioId;
        this.time = time;
        this.dataInscricao = LocalDateTime.now();
    }

    public String getCampeonatoId() {
        return campeonatoId;
    }

    public void setCampeonatoId(String campeonatoId) {
        this.campeonatoId = campeonatoId;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
