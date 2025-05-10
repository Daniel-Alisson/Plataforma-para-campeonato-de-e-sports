package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Inscricao;
import com.daniel.tde_backend.models.enums.CampeonatoStatus;
import com.daniel.tde_backend.models.enums.CampeonatoTipo;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CampeonatoDTO {

    private String id;
    @NotBlank(message = "Campo requerido")
    @Size(min = 5, max = 100, message = "O nome do campeonato deve ter entre 5 e 100 caracteres")
    private String nomeCampeonato;
    @NotBlank(message = "Campo requerido")
    @Size(max = 50, message = "O nome do jogo deve ter até 50 caracteres")
    private String nomeJogo;
    @NotNull(message = "Campo requerido")
    private CampeonatoTipo tipo;
    @NotNull(message = "Campo requerido")
    @Min(value = 2, message = "Mínimo de 2 participantes")
    @Max(value = 64, message = "Máximo de 64 participantes")
    private Integer numeroMaximoParticipantes;
    private Integer numeroInscritos;
    @PositiveOrZero(message = "O valor não pode ser negativo")
    private Double valorInscricao;
    @NotBlank(message = "Campo requerido")
    @Size(max = 30, message = "O formato deve ter até 30 caracteres")
    private String formato;
    @NotBlank(message = "Campo requerido")
    @Size(min = 10, max = 2000, message = "As regras devem ter entre 10 e 2000 caracteres")
    private String regras;
    @NotNull(message = "Campo requerido")
    @Future(message = "A data de início deve ser futura")
    private LocalDateTime dataInicio;
    @NotNull(message = "Campo requerido")
    @Future(message = "A data de término deve ser futura")
    private LocalDateTime dataTermino;
    @NotBlank(message = "Campo requerido")
    @Size(max = 200, message = "A localização deve ter até 200 caracteres")
    private String localizacao;
    private String capa;
    private String logo;
    @Size(max = 500, message = "A descrição deve ter até 500 caracteres")
    private String premiacao;
    private CampeonatoStatus status;
    @NotBlank(message = "Campo requerido")
    private String modalidade;
    private LocalDateTime dataCriacao;
    private String idPromotor;

    public CampeonatoDTO() {
    }

    public CampeonatoDTO(String id, String nomeCampeonato, String nomeJogo, CampeonatoTipo tipo, Integer numeroMaximoParticipantes, Integer numeroInscritos, Double valorInscricao, String formato, String regras, LocalDateTime dataInicio, LocalDateTime dataTermino, String localizacao, String capa, String logo, String premiacao, CampeonatoStatus status, String modalidade, LocalDateTime dataCriacao, String idPromotor) {
        this.id = id;
        this.nomeCampeonato = nomeCampeonato;
        this.nomeJogo = nomeJogo;
        this.tipo = tipo;
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
        this.numeroInscritos = numeroInscritos;
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

    public CampeonatoDTO(Campeonato entity) {
        this.id = entity.getId();
        this.nomeCampeonato = entity.getNomeCampeonato();
        this.nomeJogo = entity.getNomeJogo();
        this.tipo = entity.getTipo();
        this.numeroMaximoParticipantes = entity.getNumeroMaximoParticipantes();
        this.numeroInscritos = entity.getNumeroInscritos();
        this.valorInscricao = entity.getValorInscricao();
        this.formato = entity.getFormato();
        this.regras = entity.getRegras();
        this.dataInicio = entity.getDataInicio();
        this.dataTermino = entity.getDataTermino();
        this.localizacao = entity.getLocalizacao();
        this.capa = entity.getCapa();
        this.logo = entity.getLogo();
        this.premiacao = entity.getPremiacao();
        this.status = entity.getStatus();
        this.modalidade = entity.getModalidade();
        this.dataCriacao = entity.getDataCriacao();
        this.idPromotor = entity.getIdPromotor();
    }

    public Integer getNumeroInscritos() {
        return numeroInscritos;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getModalidade() {
        return modalidade;
    }

    public CampeonatoStatus getStatus() {
        return status;
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

    public String getPremiacao() {
        return premiacao;
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

    public Integer getNumeroMaximoParticipantes() {
        return numeroMaximoParticipantes;
    }

    public String getRegras() {
        return regras;
    }

    public CampeonatoTipo getTipo() {
        return tipo;
    }

    public Double getValorInscricao() {
        return valorInscricao;
    }

    public String getIdPromotor() {
        return idPromotor;
    }

    @Override
    public String toString() {
        return "CampeonatoDTO{" +
                "capa='" + capa + '\'' +
                ", id='" + id + '\'' +
                ", nomeCampeonato='" + nomeCampeonato + '\'' +
                ", nomeJogo='" + nomeJogo + '\'' +
                ", tipo=" + tipo +
                ", numeroMaximoParticipantes=" + numeroMaximoParticipantes +
                ", numeroInscritos =" + numeroInscritos +
                ", valorInscricao=" + valorInscricao +
                ", formato='" + formato + '\'' +
                ", regras='" + regras + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", localizacao='" + localizacao + '\'' +
                ", logo='" + logo + '\'' +
                ", premiacao='" + premiacao + '\'' +
                ", status=" + status +
                ", modalidade='" + modalidade + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", idPromotor=" + idPromotor +
                '}';
    }
}
