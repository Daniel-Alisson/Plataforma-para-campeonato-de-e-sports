package com.daniel.tde_backend.models;

import com.daniel.tde_backend.models.enums.PartidaStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "partidas")
public class Partida {

    @Id
    private String id;
    private String idCampeonato;
    private List<String> participantes;
    private PartidaStatus status;
    private LocalDateTime dataPartida;
    private String vencedor;

    public Partida() {
    }

    public Partida(String id, String idCampeonato, List<String> participantes, PartidaStatus status, LocalDateTime dataPartida, String vencedor) {
        this.id = id;
        this.idCampeonato = idCampeonato;
        this.participantes = participantes;
        this.status = status;
        this.dataPartida = dataPartida;
        this.vencedor = vencedor;
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

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public PartidaStatus getStatus() {
        return status;
    }

    public void setStatus(PartidaStatus status) {
        this.status = status;
    }

    public LocalDateTime getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDateTime dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }
}
