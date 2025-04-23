package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.InscricaoDTO;
import com.daniel.tde_backend.models.Inscricao;
import com.daniel.tde_backend.repositories.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository repository;

    public InscricaoDTO insert(InscricaoDTO dto) {
        Inscricao entity = new Inscricao();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new InscricaoDTO(entity);
    }

    private void copyDtoToEntity(InscricaoDTO dto, Inscricao entity) {
        entity.setCampeonatoId(dto.getCampeonatoId());
        entity.setUsuarioId(dto.getUsuarioId());
        entity.setTime(dto.getTime());
    }
}
