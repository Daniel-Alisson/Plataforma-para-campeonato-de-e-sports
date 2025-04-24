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

    public InscricaoDTO insert(InscricaoDTO dto) {
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
        entity = repository.save(entity);
        return new InscricaoDTO(entity);
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
