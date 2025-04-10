package com.daniel.tde_backend.dto;

import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.enums.CampeonatoTipo;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

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
    private Integer numeroMaximo;
    @PositiveOrZero(message = "O valor não pode ser negativo")
    private Double valor;
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
