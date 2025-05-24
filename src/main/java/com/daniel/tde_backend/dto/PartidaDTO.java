package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Partida;
import com.daniel.tde_backend.models.enums.PartidaStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PartidaDTO {
    private String id;
    private String idCampeonato;
    private List<String> participantes;
    private PartidaStatus status;
    private LocalDateTime dataPartida;
    private String vencedor;

    public PartidaDTO() {
    }

    public PartidaDTO(String id, String idCampeonato, List<String> participantes, PartidaStatus status, LocalDateTime dataPartida, String vencedor) {
        this.id = id;
        this.idCampeonato = idCampeonato;
        this.participantes = participantes;
        this.status = status;
        this.dataPartida = dataPartida;
        this.vencedor = vencedor;
    }

    public PartidaDTO(Partida entity) {
        this.id = entity.getId();
        this.idCampeonato = entity.getIdCampeonato();
        this.participantes = entity.getParticipantes();
        this.status = entity.getStatus();
        this.dataPartida = entity.getDataPartida();
        this.vencedor = entity.getVencedor();
    }

    public String getId() {
        return id;
    }

    public String getIdCampeonato() {
        return idCampeonato;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public PartidaStatus getStatus() {
        return status;
    }

    public LocalDateTime getDataPartida() {
        return dataPartida;
    }

    public String getVencedor() {
        return vencedor;
    }
}
