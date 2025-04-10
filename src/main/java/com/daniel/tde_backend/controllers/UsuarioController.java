package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.UsuarioDTO;
import com.daniel.tde_backend.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/usuarios/perfil")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> buscarPerfilById(@PathVariable String id) {
        UsuarioDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> buscarPerfis(Pageable pageable) {
        Page<UsuarioDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> completarPerfil(@Valid @RequestBody UsuarioDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> atualizarPerfil(@PathVariable String id, @Valid @RequestBody UsuarioDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPerfil(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}