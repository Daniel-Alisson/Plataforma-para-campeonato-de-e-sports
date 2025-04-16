package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.repositories.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public CampeonatoDTO findById(String id) {
        Campeonato campeonato = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        return new CampeonatoDTO(campeonato);
    }

    @Transactional(readOnly = true)
    public Page<CampeonatoDTO> findAll(Pageable pageable) {
        Page<Campeonato> result = repository.findAll(pageable);
        return result.map(x -> new CampeonatoDTO(x));
    }

    @Transactional
    public CampeonatoDTO update(String id, CampeonatoDTO dto) {
        Campeonato entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CampeonatoDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(String id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Campeonato não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(CampeonatoDTO dto, Campeonato entity) {
        entity.setNomeCampeonato(dto.getNomeCampeonato());
        entity.setNomeJogo(dto.getNomeJogo());
        entity.setTipo(dto.getTipo());
        entity.setNumeroMaximoParticipantes(dto.getNumeroMaximoParticipantes());
        entity.setValorInscricao(dto.getValorInscricao());
        entity.setFormato(dto.getFormato());
        entity.setRegras(dto.getRegras());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataTermino(dto.getDataTermino());
        entity.setLocalizacao(dto.getLocalizacao());
        entity.setCapa(dto.getCapa());
        entity.setLogo(dto.getLogo());
        entity.setPremiacao(dto.getPremiacao());
    }
}