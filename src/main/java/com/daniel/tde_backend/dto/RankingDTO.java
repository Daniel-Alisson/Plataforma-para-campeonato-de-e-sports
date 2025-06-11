package com.daniel.tde_backend.dto;

public class RankingDTO {

    private Integer posicao;
    private String idJogador;
    private String nomeJogador;
    private int vitorias;
    private int derrotas;
    private int partidas;

    public RankingDTO() {
    }

    public RankingDTO(String idJogador, String nomeJogador, int vitorias, int derrotas, int partidas) {
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
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

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
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