package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.dto.InscricaoDTO;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Inscricao;
import com.daniel.tde_backend.models.enums.CampeonatoStatus;
import com.daniel.tde_backend.models.enums.InscricaoStatus;
import com.daniel.tde_backend.models.enums.TipoParticipante;
import com.daniel.tde_backend.repositories.CampeonatoRepository;
import com.daniel.tde_backend.repositories.InscricaoRepository;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository repository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // TENHO Q LEMBRAR DE CRIAR UMA CLASSE EQUIPE E SUBSTITUIR O REPOSITORY AQ

    @Transactional
    public InscricaoDTO insert(InscricaoDTO dto) {
        Campeonato campeonato = campeonatoRepository.findById(dto.getIdCampeonato()).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        if (campeonato.getNumeroInscritos() >= campeonato.getNumeroMaximoParticipantes()) {
            throw new RuntimeException("Número máximo de vagas");
        }
        Inscricao entity = new Inscricao();
        entity.setIdCampeonato(dto.getIdCampeonato());
        entity.setTipo(dto.getTipo());
        if (dto.getTipo() == TipoParticipante.INDIVIDUAL) {
            entity.setIdJogador(dto.getIdJogador());
        } else if (dto.getTipo() == TipoParticipante.EQUIPE) {
            entity.setIdEquipe(dto.getIdEquipe());
        }
        entity.setDataInscricao(LocalDateTime.now());
        entity.setStatus(InscricaoStatus.PENDENTE);
        campeonato.setNumeroInscritos(campeonato.getNumeroInscritos() + 1);
        campeonatoRepository.save(campeonato);
        entity = repository.save(entity);
        return new InscricaoDTO(entity);
    }

    @Transactional(readOnly = true)
    public InscricaoDTO findById(String id) {
        Inscricao inscricao = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada"));
        return new InscricaoDTO(inscricao);
    }

    @Transactional(readOnly = true)
    public Page<InscricaoDTO> findAll(Pageable pageable) {
        Page<Inscricao> result = repository.findAll(pageable);
        return result.map(x -> new InscricaoDTO(x));
    }

    @Transactional
    public InscricaoDTO update(String id, InscricaoDTO dto) {
        Inscricao entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada"));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new InscricaoDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Inscrição não encontrada");
        }
        if (!campeonatoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Campeonato não encontrado");
        }
        try {
            Campeonato campeonato = new Campeonato();
            if (campeonato.getNumeroInscritos() > 0) {
                campeonato.setNumeroInscritos(campeonato.getNumeroInscritos() - 1);
                campeonatoRepository.save(campeonato);
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(InscricaoDTO dto, Inscricao entity) {
        entity.setIdCampeonato(dto.getIdCampeonato());
        entity.setIdJogador(dto.getIdJogador());
        entity.setIdJogador(dto.getIdJogador());
        entity.setTipo(dto.getTipo());
        entity.setStatus(dto.getStatus());
        entity.setDataInscricao(dto.getDataInscricao());
    }
}
