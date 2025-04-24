package com.daniel.tde_backend.models;

import com.daniel.tde_backend.models.enums.InscricaoStatus;
import com.daniel.tde_backend.models.enums.TipoParticipante;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "inscricoes")
public class Inscricao {

    @Id
    private String id;
    private String idCampeonato;
    private String idJogador;
    private String idEquipe;
    private TipoParticipante tipo;
    private InscricaoStatus status;
    private LocalDateTime dataInscricao;

    public Inscricao() {
        this.dataInscricao = LocalDateTime.now();
    }

    public Inscricao(String id, String idCampeonato, String idJogador, String idEquipe, TipoParticipante tipo, InscricaoStatus status) {
        this.id = id;
        this.idCampeonato = idCampeonato;
        this.idJogador = idJogador;
        this.idEquipe = idEquipe;
        this.tipo = tipo;
        this.status = status;
        this.dataInscricao = LocalDateTime.now();
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

    public String getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(String idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public InscricaoStatus getStatus() {
        return status;
    }

    public void setStatus(InscricaoStatus status) {
        this.status = status;
    }

    public TipoParticipante getTipo() {
        return tipo;
    }

    public void setTipo(TipoParticipante tipo) {
        this.tipo = tipo;
    }
}
