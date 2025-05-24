package com.daniel.tde_backend.models;

import com.daniel.tde_backend.models.enums.CampeonatoStatus;
import com.daniel.tde_backend.models.enums.CampeonatoTipo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "campeonatos")
public class Campeonato {

    @Id
    private String id;
    private String nomeCampeonato;
    private String nomeJogo;
    private CampeonatoTipo tipo;
    private Integer numeroMaximoParticipantes;
    private List<Inscricao> inscritos = new ArrayList<>();
    private Double valorInscricao;
    private String formato;
    private String regras;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private String localizacao;
    private String capa;
    private String logo;
    private String premiacao;
    private CampeonatoStatus status;
    private String modalidade;
    private LocalDateTime dataCriacao;
    private String idPromotor;
    private List<String> partidas = new ArrayList<>();

    public Campeonato() {
    }

    public Campeonato(String id, String nomeCampeonato, String nomeJogo, CampeonatoTipo tipo, Integer numeroMaximoParticipantes, List<Inscricao> inscritos, Double valorInscricao, String formato, String regras, LocalDateTime dataInicio, LocalDateTime dataTermino, String localizacao, String capa, String logo, String premiacao, CampeonatoStatus status, String modalidade, LocalDateTime dataCriacao, String idPromotor) {
        this.id = id;
        this.nomeCampeonato = nomeCampeonato;
        this.nomeJogo = nomeJogo;
        this.tipo = tipo;
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
        this.inscritos = inscritos;
        this.valorInscricao = valorInscricao;
        this.formato = formato;
        this.regras = regras;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.localizacao = localizacao;
        this.capa = capa;
        this.logo = logo;
        this.premiacao = premiacao;
        this.status = status;
        this.modalidade = modalidade;
        this.dataCriacao = dataCriacao;
        this.idPromotor = idPromotor;
    }

    public List<String> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<String> partidas) {
        this.partidas = partidas;
    }

    public List<Inscricao> getInscritos() {
        return inscritos;
    }

    public void setInscritos(List<Inscricao> inscritos) {
        this.inscritos = inscritos;
    }

    public void setValorInscricao(Double valorInscricao) {
        this.valorInscricao = valorInscricao;
    }

    public Double getValorInscricao() {
        return valorInscricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public CampeonatoStatus getStatus() {
        return status;
    }

    public void setStatus(CampeonatoStatus status) {
        this.status = status;
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

    public String getIdPromotor() {
        return idPromotor;
    }

    public void setIdPromotor(String idPromotor) {
        this.idPromotor = idPromotor;
    }
}
