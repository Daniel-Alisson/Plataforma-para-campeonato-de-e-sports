package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.enums.CampeonatoTipo;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class CampeonatoDTO {

    @Id
    private String id;
    private String nomeCampeonato;
    private String nomeJogo;
    private CampeonatoTipo tipo;
    private Integer numeroMaximo;
    private Double valor;
    private String formato;
    private String regras;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private String localizacao;
    private String capa;
    private String logo;
    private String descricao;

    public CampeonatoDTO() {
    }

    public CampeonatoDTO(String id, String nomeCampeonato, String nomeJogo, CampeonatoTipo tipo, Integer numeroMaximo, Double valor, String formato, String regras, LocalDateTime dataInicio, LocalDateTime dataTermino, String localizacao, String capa, String logo, String descricao) {
        this.id = id;
        this.nomeCampeonato = nomeCampeonato;
        this.nomeJogo = nomeJogo;
        this.tipo = tipo;
        this.numeroMaximo = numeroMaximo;
        this.valor = valor;
        this.formato = formato;
        this.regras = regras;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.localizacao = localizacao;
        this.capa = capa;
        this.logo = logo;
        this.descricao = descricao;
    }

    public CampeonatoDTO(Campeonato entity) {
        this.id = entity.getId();
        this.nomeCampeonato = entity.getNomeCampeonato();
        this.nomeJogo = entity.getNomeJogo();
        this.tipo = entity.getTipo();
        this.numeroMaximo = entity.getNumeroMaximo();
        this.valor = entity.getValor();
        this.formato = entity.getFormato();
        this.regras = entity.getRegras();
        this.dataInicio = entity.getDataInicio();
        this.dataTermino = entity.getDataTermino();
        this.localizacao = entity.getLocalizacao();
        this.capa = entity.getCapa();
        this.logo = entity.getLogo();
        this.descricao = entity.getDescricao();
    }

    public String getCapa() {
        return capa;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFormato() {
        return formato;
    }

    public String getId() {
        return id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getLogo() {
        return logo;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public Integer getNumeroMaximo() {
        return numeroMaximo;
    }

    public String getRegras() {
        return regras;
    }

    public CampeonatoTipo getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }
}
