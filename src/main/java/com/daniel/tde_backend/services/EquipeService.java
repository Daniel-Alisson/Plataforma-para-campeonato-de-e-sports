package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.EquipeDTO;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Equipe;
import com.daniel.tde_backend.repositories.EquipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EquipeService {

    private EquipeRepository repository;

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
}
