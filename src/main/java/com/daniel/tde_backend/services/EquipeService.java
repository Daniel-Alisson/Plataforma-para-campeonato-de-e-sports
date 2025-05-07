package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.dto.EquipeDTO;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Equipe;
import com.daniel.tde_backend.models.enums.CampeonatoStatus;
import com.daniel.tde_backend.repositories.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository repository;

    @Transactional
    public EquipeDTO insert(EquipeDTO dto) {
        Equipe entity = new Equipe();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new EquipeDTO(entity);
    }
    // uma equipe por usuario, implementar dps

    @Transactional(readOnly = true)
    public EquipeDTO findById(String id) {
        Equipe equipe = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipe não encontrada"));
        return new EquipeDTO(equipe);
    }

    @Transactional(readOnly = true)
    public Page<EquipeDTO> findAll(Pageable pageable) {
        Page<Equipe> result = repository.findAll(pageable);
        return result.map(x -> new EquipeDTO(x));
    }

    private void copyDtoToEntity(EquipeDTO dto, Equipe entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setMembros(dto.getMembros());
        entity.setDataCriacao(LocalDateTime.now());
        entity.setLogoUrl(dto.getLogoUrl());
    }
}
