package com.daniel.tde_backend.services;

import com.daniel.tde_backend.config.auth.AuthorizationService;
import com.daniel.tde_backend.dto.PartidaDTO;
import com.daniel.tde_backend.exceptions.InsufficientPlayersException;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Inscricao;
import com.daniel.tde_backend.models.Partida;
import com.daniel.tde_backend.models.enums.PartidaStatus;
import com.daniel.tde_backend.repositories.CampeonatoRepository;
import com.daniel.tde_backend.repositories.InscricaoRepository;
import com.daniel.tde_backend.repositories.PartidaRepository;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository repository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthorizationService service;

    @Transactional
    public List<PartidaDTO> criarPartidas(String idCampeonato) {
        // TENHO Q ADICIONAR UMA VERIFICAÇÃO DPS DE AUTENTICAÇÃO
        Campeonato campeonato = campeonatoRepository.findById(idCampeonato).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado"));
        List<Inscricao> inscritos = inscricaoRepository.findByIdCampeonato(idCampeonato);
        if (inscritos.size() < 2) {
            throw new InsufficientPlayersException("Jogadores insuficientes para criar partidas");
        }

        List<String> participantes = inscritos.stream().map(i -> i.getIdJogador() != null ? i.getIdJogador() : i.getIdEquipe()).collect(Collectors.toList());
        if (participantes.size() % 2 != 0) {
            throw new InsufficientPlayersException("Número de participantes é ímpar");
        }

        Collections.shuffle(participantes);
        List<Partida> partidasCriadas = new ArrayList<>();
        for (int i = 0; i < participantes.size(); i += 2) {
            String participante1 = participantes.get(i);
            String participante2 = participantes.get(i + 1);

            Partida partida = new Partida();
            partida.setIdCampeonato(idCampeonato);
            partida.setParticipantes(List.of(participante1, participante2));
            partida.setStatus(PartidaStatus.AGENDADA);
            partida.setDataPartida(LocalDateTime.now().plusDays(2));
            partida = repository.save(partida);
            partidasCriadas.add(partida);

            campeonato.getPartidas().add(partida.getId());
        }
        campeonatoRepository.save(campeonato);
        return partidasCriadas.stream().map(PartidaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<PartidaDTO> listaPartidas() {
        String idJogador = service.getIdAuthenticated();
        List<Partida> partidas = repository.findByParticipantesContains(idJogador);
        return partidas.stream().map(PartidaDTO::new).toList();
    }
}
