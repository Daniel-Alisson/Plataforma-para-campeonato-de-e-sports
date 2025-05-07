package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.dto.EquipeDTO;
import com.daniel.tde_backend.services.EquipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/equipes")
public class EquipeController {

    @Autowired
    private EquipeService service;

    @PostMapping
    public ResponseEntity<EquipeDTO> criarEquipe(@Valid @RequestBody EquipeDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipeDTO> buscarEquipeById(@PathVariable String id) {
        EquipeDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<EquipeDTO>> listarEquipes(Pageable pageable) {
        Page<EquipeDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
}
