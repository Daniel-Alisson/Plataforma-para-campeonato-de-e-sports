package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.dto.UsuarioDTO;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.models.enums.CampeonatoTipo;
import com.daniel.tde_backend.repositories.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository repository;

    @Transactional
    public CampeonatoDTO insert(CampeonatoDTO dto) {
        Campeonato entity = new Campeonato();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CampeonatoDTO(entity);
    }

    private void copyDtoToEntity(CampeonatoDTO dto, Campeonato entity) {
        entity.setNomeCampeonato(dto.getNomeCampeonato());
        entity.setNomeJogo(dto.getNomeJogo());
        entity.setTipo(dto.getTipo());
        entity.setNumeroMaximo(dto.getNumeroMaximo());
        entity.setValor(dto.getValor());
        entity.setFormato(dto.getFormato());
        entity.setRegras(dto.getRegras());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataTermino(dto.getDataTermino());
        entity.setLocalizacao(dto.getLocalizacao());
        entity.setCapa(dto.getCapa());
        entity.setLogo(dto.getLogo());
        entity.setDescricao(dto.getDescricao());
    }
}
