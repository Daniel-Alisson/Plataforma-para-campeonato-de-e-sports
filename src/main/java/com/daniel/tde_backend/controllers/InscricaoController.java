package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.dto.InscricaoDTO;
import com.daniel.tde_backend.dto.UsuarioDTO;
import com.daniel.tde_backend.services.CampeonatoService;
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
@RequestMapping(value = "/inscricao")
public class InscricaoController {

    // USAR PARA TESTES
    // ID USUARIO - 6802f99f03892277089ce38a
    // ID CAMPEONATO - 68099f54062b737d8d2585ae

    @Autowired
    private InscricaoService service;

    @Autowired
    private CampeonatoService campeonatoService;

    @PostMapping("/{id}")
    public ResponseEntity<?> inscreverCampeonato(@PathVariable String id, @Valid @RequestBody InscricaoDTO dto) {
        Map<String, Object> response = new HashMap<>();
        dto = service.insert(dto);
        CampeonatoDTO campeonatoDTO = campeonatoService.findById(id);

        response.put("Comprovante: ", dto.getStatus());
        response.put("Número de inscritos: ", campeonatoDTO.getNumeroInscritos());
        response.put("Número de vagas: ", campeonatoDTO.getNumeroMaximoParticipantes());
        response.put("Detalhes do campeonato: ", campeonatoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<InscricaoDTO>> findAll(Pageable pageable) {
        Page<InscricaoDTO> list = service.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDTO> findById(@PathVariable String id) {
        InscricaoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscricaoDTO> update(@PathVariable String id, @Valid @RequestBody InscricaoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

