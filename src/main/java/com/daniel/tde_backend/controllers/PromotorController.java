package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.CampeonatoDTO;
import com.daniel.tde_backend.services.PromotorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/promotor")
public class PromotorController {

    @Autowired
    private PromotorService service;

    @PostMapping
    public ResponseEntity<CampeonatoDTO> criarCampeonato(@Valid @RequestBody CampeonatoDTO dto) {
        String idPromotor = emailAuthenticated();
        CampeonatoDTO campeonato = service.criarCampeonato(dto, idPromotor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(campeonato.getId()).toUri();
        return ResponseEntity.created(uri).body(campeonato);
    }

    @GetMapping("/meus-campeonatos")
    public ResponseEntity<Page<CampeonatoDTO>> listarPorPromotor(Pageable pageable) {
        String idPromotor = emailAuthenticated();
        Page<CampeonatoDTO> list = service.listar(idPromotor, pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/meus-campeonatos/{id}")
    public ResponseEntity<CampeonatoDTO> buscarCampeonatos(@PathVariable String id) {
        String idPromotor = emailAuthenticated();
        CampeonatoDTO dto = service.findByid(id, idPromotor);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/meus-campeonatos/{id}")
    public ResponseEntity<CampeonatoDTO> atualizarCampeonato(@PathVariable String id, @Valid @RequestBody CampeonatoDTO dto) {
        String idPromotor = emailAuthenticated();
        dto = service.update(id, dto, idPromotor);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/meus-campeonatos/{id}")
    public ResponseEntity<Void> deletarCampeonato(@PathVariable String id) {
        String idPromotor = emailAuthenticated();
        service.delete(id, idPromotor);
        return ResponseEntity.noContent().build();
    }

    // Dps eu vou ter mudar isso, está utilizando o email como autenticação vou tentar mudar para id
    private String emailAuthenticated() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter o email do usuário autenticado");
        }
    }
}

