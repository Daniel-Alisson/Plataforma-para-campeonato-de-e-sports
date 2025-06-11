package com.daniel.tde_backend.dto;

public class RankingEquipeDTO {

    private Integer posicao;
    private String idEquipe;
    private String nomeEquipe;
    private int vitorias;
    private int derrotas;
    private int partidas;

    public RankingEquipeDTO() {
    }

    public RankingEquipeDTO(String idEquipe, String nomeEquipe, int vitorias, int derrotas, int partidas) {
        this.idEquipe = idEquipe;
        this.nomeEquipe = nomeEquipe;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.partidas = partidas;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public String getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getPartidas() {
        return partidas;
    }

    public void setPartidas(int partidas) {
        this.partidas = partidas;
    }
}
