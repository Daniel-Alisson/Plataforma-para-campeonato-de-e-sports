package com.daniel.tde_backend.services;

import com.daniel.tde_backend.config.auth.AuthorizationService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private AuthorizationService service;

    @Transactional
    public InscricaoDTO insert(String idCampeonato, InscricaoDTO dto) {
        Campeonato campeonato = campeonatoRepository.findById(idCampeonato).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        if (campeonato.getInscritos().size() >= campeonato.getNumeroMaximoParticipantes()) {
            throw new VagasEsgotadasException("Número máximo de vagas atingido [" + campeonato.getNumeroMaximoParticipantes() + "]");
        } if (campeonato.getTipo() == CampeonatoTipo.INDIVIDUAL) {
            if (dto.getIdJogador() == null || dto.getIdEquipe() != null) {
                throw new InvalidInscricaoException("O campeonato individual requer o ID do jogador e não pode conter o ID da equipe");
            }
        } else if (campeonato.getTipo() == CampeonatoTipo.EQUIPE) {
            if (dto.getIdEquipe() == null || dto.getIdJogador() != null) {
                throw new InvalidInscricaoException("O campeonato por equipe requer o ID da equipe e não pode conter o ID do jogador");
            }
        } else {
            throw new InvalidInscricaoException("Tipo de participante inválido");
        }

        if (campeonato.getStatus() != CampeonatoStatus.ABERTO) {
            throw new ClosedInscricaoException("Campeonato não permite mais inscrições");
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
        entity.setIdCampeonato(idCampeonato);
        entity.setDataInscricao(LocalDateTime.now());
        entity.setStatus(InscricaoStatus.PENDENTE);
        entity = repository.save(entity);
        campeonato.getInscritos().add(entity);
        campeonatoRepository.save(campeonato);
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
        Campeonato campeonato = campeonatoRepository.findById(entity.getIdCampeonato()).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        /*
        if(dto.getStatus() == InscricaoStatus.REPROVADO) {
            repository.delete(entity);
        }
        */
        entity.setStatus(dto.getStatus());
        entity = repository.save(entity);
        campeonato.getInscritos().stream().filter(x -> x.getId().equals(id)).findFirst().ifPresent(x -> {
            x.setStatus(dto.getStatus());
        });
        campeonatoRepository.save(campeonato);
        return new InscricaoDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(String id) {
        Inscricao entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada"));
        Campeonato campeonato = campeonatoRepository.findById(entity.getIdCampeonato()).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        campeonato.getInscritos().removeIf(x -> x.getId().equals(id));
        campeonatoRepository.save(campeonato);
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CampeonatoDTO> campeonatosInscritos() {
        String idJogador = service.getIdAuthenticated();
        List<Inscricao> inscricoes = repository.findByIdJogador(idJogador);
        if (inscricoes.isEmpty()) {
            System.out.println("Jogador não possui inscrições.");
        }

        List<String> idCampeonatos = inscricoes.stream().map(Inscricao::getIdCampeonato).toList();
        List<Campeonato> campeonatos = campeonatoRepository.findAllById(idCampeonatos);
        return campeonatos.stream().map(CampeonatoDTO::new).toList();
    }

    private void copyDtoToEntity(InscricaoDTO dto, Inscricao entity) {
        entity.setIdCampeonato(dto.getIdCampeonato());
        entity.setIdJogador(dto.getIdJogador());
        entity.setIdEquipe(dto.getIdEquipe());
        entity.setStatus(dto.getStatus());
        entity.setDataInscricao(dto.getDataInscricao());
    }
}
