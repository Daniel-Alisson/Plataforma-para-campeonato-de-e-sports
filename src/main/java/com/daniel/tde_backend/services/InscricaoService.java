package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.dto.InscricaoDTO;
import com.daniel.tde_backend.exceptions.ClosedInscricaoException;
import com.daniel.tde_backend.exceptions.InvalidInscricaoException;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.exceptions.VagasEsgotadasException;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Equipe;
import com.daniel.tde_backend.models.Inscricao;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.models.enums.CampeonatoStatus;
import com.daniel.tde_backend.models.enums.CampeonatoTipo;
import com.daniel.tde_backend.models.enums.InscricaoStatus;
import com.daniel.tde_backend.repositories.CampeonatoRepository;
import com.daniel.tde_backend.repositories.EquipeRepository;
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

    @Autowired
    private EquipeRepository equipeRepository;

    @Transactional
    public InscricaoDTO insert(InscricaoDTO dto) {
        Campeonato campeonato = campeonatoRepository.findById(dto.getIdCampeonato()).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        if (campeonato.getNumeroInscritos() >= campeonato.getNumeroMaximoParticipantes()) {
            throw new VagasEsgotadasException("Número máximo de vagas atingido [" + campeonato.getNumeroMaximoParticipantes() + "]");
        } if (campeonato.getTipo() == CampeonatoTipo.INDIVIDUAL) {
            if (dto.getIdJogador() == null || dto.getIdEquipe() != null) {
                throw new InvalidInscricaoException("O campeonato individual requer o ID do jogador e não pode conter o ID da equipe");
            }
        } if (campeonato.getTipo() == CampeonatoTipo.EQUIPE) {
            if (dto.getIdEquipe() == null || dto.getIdJogador() != null) {
                throw new InvalidInscricaoException("O campeonato por equipe requer o ID da equipe e não pode conter o ID do jogador");
            }
        } if (campeonato.getStatus() != CampeonatoStatus.ABERTO) {
            if (campeonato.getStatus() == CampeonatoStatus.EM_ANDAMENTO) {
                throw new ClosedInscricaoException("Campeonato está em andamento");
            } else if(campeonato.getStatus() == CampeonatoStatus.FINALIZADO) {
                throw new ClosedInscricaoException("Campeonato finalizado");
            }
        } else {
            throw new InvalidInscricaoException("Tipo de participante inválido");
        }

        Inscricao entity = new Inscricao();
        entity.setIdCampeonato(dto.getIdCampeonato());
        if (campeonato.getTipo() == CampeonatoTipo.INDIVIDUAL) {
            Usuario usuario = usuarioRepository.findById(dto.getIdJogador()).orElseThrow(() -> new ResourceNotFoundException("Jogador não encontrado"));
            entity.setIdJogador(dto.getIdJogador());
        } else {
            Equipe equipe = equipeRepository.findById(dto.getIdEquipe()).orElseThrow(() -> new ResourceNotFoundException("Equipe não encontrada"));
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
        if(dto.getStatus() == InscricaoStatus.REPROVADO) {
            repository.delete(entity);
        }
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
        entity.setIdEquipe(dto.getIdEquipe());
        entity.setStatus(dto.getStatus());
        entity.setDataInscricao(dto.getDataInscricao());
    }
}
