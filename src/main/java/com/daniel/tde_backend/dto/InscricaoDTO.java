package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Inscricao;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class InscricaoDTO {

    @NotBlank(message = "Campo requerido")
    private String campeonatoId;
    @NotBlank(message = "Campo requerido")
    private String usuarioId;
    private String time;

    public InscricaoDTO() {
    }

    public InscricaoDTO(String campeonatoId, String usuarioId, String time) {
        this.campeonatoId = campeonatoId;
        this.usuarioId = usuarioId;
        this.time = time;
    }

    public InscricaoDTO(Inscricao entity) {
        this.campeonatoId = entity.getCampeonatoId();
        this.usuarioId = entity.getUsuarioId();
        this.time = entity.getTime();
    }

    public String getCampeonatoId() {
        return campeonatoId;
    }

    public void setCampeonatoId(String campeonatoId) {
        this.campeonatoId = campeonatoId;
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
