package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.exceptions.InvalidAccessException;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.enums.CampeonatoStatus;
import com.daniel.tde_backend.repositories.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PromotorService {

    @Autowired
    private CampeonatoRepository repository;

    @Transactional
    public CampeonatoDTO criarCampeonato(CampeonatoDTO dto, String idPromotor) {
        Campeonato entity = new Campeonato();
        copyDtoToEntity(dto, entity);
        entity.setStatus(CampeonatoStatus.ABERTO);
        entity.setDataCriacao(LocalDateTime.now());
        entity.setIdPromotor(idPromotor);

        entity = repository.save(entity);
        return new CampeonatoDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<CampeonatoDTO> listar(String idPromotor, Pageable pageable) {
        Page<Campeonato> result = repository.findByPromotorId(idPromotor, pageable);
        return result.map(x -> new CampeonatoDTO(x));
    }

    @Transactional(readOnly = true)
    public CampeonatoDTO findByid(String id, String idPromotor) {
        Campeonato entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        return new CampeonatoDTO(entity);
    }

    @Transactional
    public CampeonatoDTO update(String id, CampeonatoDTO dto, String idPromotor) {
        Campeonato entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        if (!entity.getIdPromotor().equals(idPromotor)) {
            throw new InvalidAccessException("Você não tem permissão para acessar este campeonato");
        }
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CampeonatoDTO(entity);
    }

    @Transactional
    public void delete(String id, String idPromotor) {
        Campeonato entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        if (!entity.getIdPromotor().equals(idPromotor)) {
            throw new InvalidAccessException("Você não tem permissão para acessar este campeonato");
        }
        repository.deleteById(id);
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
        entity.setStatus(dto.getStatus());
        entity.setModalidade(dto.getModalidade());
        entity.setDataCriacao(dto.getDataCriacao());
    }
}
