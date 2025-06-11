package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.RankingDTO;
import com.daniel.tde_backend.dto.RankingEquipeDTO;
import com.daniel.tde_backend.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/individual")
    public ResponseEntity<List<RankingDTO>> getRankingIndividual() {
        return ResponseEntity.ok(rankingService.gerarRankingIndividual());
    }

    @GetMapping("/equipe")
    public ResponseEntity<List<RankingEquipeDTO>> getRankingEquipe() {
        return ResponseEntity.ok(rankingService.gerarRankingEquipe());
    }
}
