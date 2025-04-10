package com.daniel.tde_backend.models;

import com.daniel.tde_backend.models.enums.CampeonatoTipo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "campeonatos")
public class Campeonato {

    @Id
    private String id;
    private String nomeCampeonato;
    private String nomeJogo;
    private CampeonatoTipo tipo;
    private Integer numeroMaximoParticipantes;
    private Double valorInscricao;
    private String formato;
    private String regras;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private String localizacao;
    private String capa;
    private String logo;
    private String premiacao;

    public Campeonato() {
    }

    public Campeonato(String id, String nomeCampeonato, String nomeJogo, CampeonatoTipo tipo, Integer numeroMaximoParticipantes, Double valorInscricao, String formato, String regras, LocalDateTime dataInicio, LocalDateTime dataTermino, String localizacao, String capa, String logo, String premiacao) {
        this.id = id;
        this.nomeCampeonato = nomeCampeonato;
        this.nomeJogo = nomeJogo;
        this.tipo = tipo;
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
        this.valorInscricao = valorInscricao;
        this.formato = formato;
        this.regras = regras;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.localizacao = localizacao;
        this.capa = capa;
        this.logo = logo;
        this.premiacao = premiacao;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(String premiacao) {
        this.premiacao = premiacao;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public Integer getNumeroMaximoParticipantes() {
        return numeroMaximoParticipantes;
    }

    public void setNumeroMaximoParticipantes(Integer numeroMaximoParticipantes) {
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
    }

    public String getRegras() {
        return regras;
    }

    public void setRegras(String regras) {
        this.regras = regras;
    }

    public CampeonatoTipo getTipo() {
        return tipo;
    }

    public void setTipo(CampeonatoTipo tipo) {
        this.tipo = tipo;
    }

    public Double getValorInscricao() {
        return valorInscricao;
    }

    public void setValorInscricao(Double valorInscricao) {
        this.valorInscricao = valorInscricao;
    }
}
