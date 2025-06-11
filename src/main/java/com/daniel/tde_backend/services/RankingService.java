package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.RankingDTO;
import com.daniel.tde_backend.dto.RankingEquipeDTO;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Partida;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.models.enums.CampeonatoTipo;
import com.daniel.tde_backend.models.enums.PartidaStatus;
import com.daniel.tde_backend.repositories.CampeonatoRepository;
import com.daniel.tde_backend.repositories.PartidaRepository;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RankingService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    public List<RankingDTO> gerarRankingIndividual() {
        List<Partida> partidas = partidaRepository.findAll();
        Map<String, RankingDTO> ranking = new HashMap<>();
        for (Partida partida : partidas) {
            Campeonato campeonato = campeonatoRepository.findById(partida.getIdCampeonato()).orElse(null);
            if (campeonato == null || campeonato.getTipo() != CampeonatoTipo.INDIVIDUAL) {
                continue;
            }
            if (partida.getStatus() != PartidaStatus.FINALIZADA) {
                continue;
            }

            List<String> participantes = partida.getParticipantes();
            String vencedorId = partida.getVencedor();
            for (String participanteId : participantes) {
                RankingDTO dados = ranking.getOrDefault(participanteId, new RankingDTO(participanteId, "", 0, 0, 0));
                dados.setPartidas(dados.getPartidas() + 1);
                if (participanteId.equals(vencedorId)) {
                    dados.setVitorias(dados.getVitorias() + 1);
                } else {
                    dados.setDerrotas(dados.getDerrotas() + 1);
                }

                ranking.put(participanteId, dados);
            }
        }

        List<RankingDTO> rankingOrdenado = ranking.values().stream().sorted(Comparator.comparingInt(RankingDTO::getVitorias).reversed()).toList();
        int posicao = 1;
        for (RankingDTO r : rankingOrdenado) {
            r.setPosicao(posicao++);
        }

        return rankingOrdenado;
    }

    public List<RankingEquipeDTO> gerarRankingEquipe() {
        List<Partida> partidas = partidaRepository.findAll();
        Map<String, RankingEquipeDTO> ranking = new HashMap<>();
        for (Partida partida : partidas) {
            Campeonato campeonato = campeonatoRepository.findById(partida.getIdCampeonato()).orElse(null);
            if (campeonato == null || campeonato.getTipo() != CampeonatoTipo.EQUIPE) {
                continue;
            }

            if (partida.getStatus() != PartidaStatus.FINALIZADA) {
                continue;
            }

            List<String> equipes = partida.getParticipantes();
            String equipeVencedoraId = partida.getVencedor();
            for (String equipeId : equipes) {
                RankingEquipeDTO dados = ranking.getOrDefault(equipeId, new RankingEquipeDTO(equipeId, "", 0, 0, 0));
                dados.setPartidas(dados.getPartidas() + 1);
                if (equipeId.equals(equipeVencedoraId)) {
                    dados.setVitorias(dados.getVitorias() + 1);
                } else {
                    dados.setDerrotas(dados.getDerrotas() + 1);
                }

                ranking.put(equipeId, dados);
            }
        }

        List<RankingEquipeDTO> rankingOrdenado = ranking.values().stream().sorted(Comparator.comparingInt(RankingEquipeDTO::getVitorias).reversed()).toList();
        int posicao = 1;
        for (RankingEquipeDTO r : rankingOrdenado) {
            r.setPosicao(posicao++);
        }

        return rankingOrdenado;
    }
}
