package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.services.CampeonatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoService service;

    @PostMapping
    public ResponseEntity<CampeonatoDTO> criarCampeonato(@Valid @RequestBody CampeonatoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CampeonatoDTO> buscarCampeonatoById(@PathVariable String id) {
        CampeonatoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<CampeonatoDTO>> listarCampeonatos(Pageable pageable) {
        Page<CampeonatoDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CampeonatoDTO> atualizarCampeonato(@PathVariable String id, @Valid @RequestBody CampeonatoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCampeonato(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}