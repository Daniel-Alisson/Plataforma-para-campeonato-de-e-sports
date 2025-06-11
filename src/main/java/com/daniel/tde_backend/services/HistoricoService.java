package com.daniel.tde_backend.services;

import com.daniel.tde_backend.config.auth.AuthorizationService;
import com.daniel.tde_backend.dto.HistoricoDTO;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Inscricao;
import com.daniel.tde_backend.models.Partida;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.repositories.CampeonatoRepository;
import com.daniel.tde_backend.repositories.InscricaoRepository;
import com.daniel.tde_backend.repositories.PartidaRepository;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoricoService {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InscricaoRepository repository;

    @Autowired
    private PartidaRepository partidaRepository;

    public List<HistoricoDTO> obterHistoricoDoJogadorLogado() {
        String jogadorId = authorizationService.getIdAuthenticated();
        List<Inscricao> inscricoes = repository.findByIdJogador(jogadorId);
        List<String> idsCampeonatos = inscricoes.stream().map(Inscricao::getIdCampeonato).distinct().collect(Collectors.toList());
        List<HistoricoDTO> listaHistorico = new ArrayList<>();

        for (String campeonatoId : idsCampeonatos) {
            Campeonato campeonato = campeonatoRepository.findById(campeonatoId).orElseThrow(() -> new ResourceNotFoundException("Campeonato não encontrado: " + campeonatoId));
            List<Partida> partidas = partidaRepository.findByIdCampeonato(campeonatoId);
            int vitorias = 0;
            int derrotas = 0;

            for (Partida partida : partidas) {
                String vencedorId = partida.getVencedor();
                List<String> participantes = partida.getParticipantes();
                if (vencedorId != null) {
                    if (vencedorId.equals(jogadorId)) {
                        vitorias++;
                    } else if (participantes.contains(jogadorId)) {
                        derrotas++;
                    }
                }
            }

            HistoricoDTO dto = new HistoricoDTO();
            dto.setIdCampeonato(campeonato.getId());
            dto.setNomeCampeonato(campeonato.getNomeCampeonato());
            dto.setVitorias(vitorias);
            dto.setDerrotas(derrotas);
            listaHistorico.add(dto);
        }

        return listaHistorico;
    }
}