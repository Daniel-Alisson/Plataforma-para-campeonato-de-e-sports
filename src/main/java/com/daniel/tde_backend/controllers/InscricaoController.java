package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.config.auth.AuthorizationService;
import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.dto.InscricaoDTO;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Inscricao;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import com.daniel.tde_backend.services.CampeonatoService;
import com.daniel.tde_backend.services.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/inscricao")
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    @Autowired
    private CampeonatoService campeonatoService;

    @PostMapping("/{idCampeonato}")
    public ResponseEntity<?> inscreverCampeonato(@PathVariable String idCampeonato, @Valid @RequestBody InscricaoDTO dto) {
        Map<String, Object> response = new HashMap<>();
        dto = service.insert(idCampeonato, dto);
        CampeonatoDTO campeonatoDTO = campeonatoService.findById(idCampeonato);

        response.put("Comprovante: ", dto.getStatus());
        response.put("Número de inscritos: ", campeonatoDTO.getInscritos().size());
        response.put("Número de vagas: ", campeonatoDTO.getNumeroMaximoParticipantes());
        response.put("Detalhes do campeonato: ", campeonatoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<InscricaoDTO>> listarInscricoes(Pageable pageable) {
        Page<InscricaoDTO> list = service.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDTO> listarInscricaoPorId(@PathVariable String id) {
        InscricaoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscricaoDTO> atualizarInscricao(@PathVariable String id, @Valid @RequestBody InscricaoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerInscricao(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CampeonatoDTO>> listarCampeonatosInscritos() {
        List<CampeonatoDTO> lista = service.campeonatosInscritos();
        return ResponseEntity.ok(lista);
    }
}
