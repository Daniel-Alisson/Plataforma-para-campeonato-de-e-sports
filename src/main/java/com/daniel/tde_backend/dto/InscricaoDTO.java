package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Inscricao;
import com.daniel.tde_backend.models.enums.InscricaoStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class InscricaoDTO {

    private String id;
    private String idCampeonato;
    private String idJogador;
    private String idEquipe;
    private InscricaoStatus status;
    private LocalDateTime dataInscricao;

    public InscricaoDTO() {
    }

    public InscricaoDTO(String id, String idCampeonato, String idJogador, String idEquipe, InscricaoStatus status, LocalDateTime dataInscricao) {
        this.id = id;
        this.idCampeonato = idCampeonato;
        this.idJogador = idJogador;
        this.idEquipe = idEquipe;
        this.status = status;
        this.dataInscricao = dataInscricao;
    }

    public InscricaoDTO(Inscricao entity) {
        this.id = entity.getId();
        this.idCampeonato = entity.getIdCampeonato();
        this.idJogador = entity.getIdJogador();
        this.idEquipe = entity.getIdEquipe();
        this.status = entity.getStatus();
        this.dataInscricao = entity.getDataInscricao();
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public String getId() {
        return id;
    }

    public String getIdCampeonato() {
        return idCampeonato;
    }

    public String getIdEquipe() {
        return idEquipe;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public InscricaoStatus getStatus() {
        return status;
    }
}
