package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.dto.InscricaoDTO;
import com.daniel.tde_backend.dto.UsuarioDTO;
import com.daniel.tde_backend.services.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    @PostMapping
    public ResponseEntity<InscricaoDTO> inscreverCampeonato(@Valid @RequestBody InscricaoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /*@PostMapping
    public ResponseEntity<?> inscreverCampeonato(@Valid @RequestBody InscricaoDTO dto) {
        Map<String, Object> response = new HashMap<>();
        dto = service.insert(dto);
        response.put("Inscricao realizada: ", "DADOS DA INSCRICAO\n"+ dto);
        response.put("Dados", dto.getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

     */
}
