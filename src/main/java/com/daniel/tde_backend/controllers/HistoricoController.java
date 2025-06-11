package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.HistoricoDTO;
import com.daniel.tde_backend.models.Campeonato;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.services.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    public ResponseEntity<List<HistoricoDTO>> obterHistoricoDoJogadorLogado() {
        List<HistoricoDTO> historico = historicoService.obterHistoricoDoJogadorLogado();

        if (historico.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historico);
    }
}
