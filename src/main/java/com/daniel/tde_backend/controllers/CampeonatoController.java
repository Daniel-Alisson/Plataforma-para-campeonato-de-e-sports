package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.services.CampeonatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoService service;

    public ResponseEntity<CampeonatoDTO> insert(@Valid @RequestBody CampeonatoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
