package com.daniel.tde_backend.dto;

public class HistoricoDTO {

    private String idCampeonato;
    private String nomeCampeonato;
    private String tipoCampeonato;
    private Integer vitorias;
    private Integer derrotas;

    public HistoricoDTO() {
    }

    public HistoricoDTO(String idCampeonato, String nomeCampeonato, String tipoCampeonato, Integer vitorias, Integer derrotas) {
        this.idCampeonato = idCampeonato;
        this.nomeCampeonato = nomeCampeonato;
        this.tipoCampeonato = tipoCampeonato;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
    }

    public String getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(String idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public String getTipoCampeonato() {
        return tipoCampeonato;
    }

    public void setTipoCampeonato(String tipoCampeonato) {
        this.tipoCampeonato = tipoCampeonato;
    }

    public Integer getVitorias() {
        return vitorias;
    }

    public void setVitorias(Integer vitorias) {
        this.vitorias = vitorias;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(Integer derrotas) {
        this.derrotas = derrotas;
    }
}
