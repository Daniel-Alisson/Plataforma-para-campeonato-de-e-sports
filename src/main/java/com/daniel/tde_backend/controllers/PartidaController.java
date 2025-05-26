package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.PartidaDTO;
import com.daniel.tde_backend.models.Partida;
import com.daniel.tde_backend.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    @Autowired
    private PartidaService service;

    @PostMapping("/criar/{idCampeonato}")
    public ResponseEntity<List<PartidaDTO>> criarPartidas(@PathVariable String idCampeonato) {
        List<PartidaDTO> partidas = service.criarPartidas(idCampeonato);
        return ResponseEntity.status(HttpStatus.CREATED).body(partidas);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PartidaDTO>> listarPartidas() {
        List<PartidaDTO> partidas = service.listaPartidas();
        return ResponseEntity.ok(partidas);
    }

    @GetMapping("/campeonato/{idCampeonato}")
    public ResponseEntity<List<PartidaDTO>> listarPartidasCampeonato(@PathVariable String idCampeonato) {
        List<PartidaDTO> partidas = service.listarPartidasCampeonato(idCampeonato);
        return ResponseEntity.ok(partidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidaDTO> buscarPartidasById(@PathVariable String id) {
        PartidaDTO partida = service.buscarPartidasById(id);
        return ResponseEntity.ok(partida);
    }

    @PatchMapping("/resultado/{id}")
    public ResponseEntity<PartidaDTO> definirResultado(@PathVariable String id, @RequestBody PartidaDTO dto) {
        PartidaDTO partida = service.definirVencedor(id, dto);
        return ResponseEntity.ok(partida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPartida(@PathVariable String id) {
        service.cancelarPartida(id);
        return ResponseEntity.noContent().build();
    }

}
