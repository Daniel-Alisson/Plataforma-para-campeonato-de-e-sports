package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.UsuarioCadastroDTO;
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
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioCadastroDTO dto) {
        UsuarioDTO usuario = service.cadastrarUsuario(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping(value = "/perfil/{id}")
    public ResponseEntity<UsuarioDTO> buscarPerfil(@PathVariable String id) {
        UsuarioDTO dto = service.buscarUsuarioPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/perfil")
    public ResponseEntity<Page<UsuarioDTO>> listarUsuarios(Pageable pageable) {
        Page<UsuarioDTO> dto = service.buscarUsuarios(pageable);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/perfil/{id}")
    public ResponseEntity<UsuarioDTO> atualizarPerfil(@PathVariable String id, @Valid @RequestBody UsuarioDTO dto) {
        dto = service.atualizarUsuario(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/perfil/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}