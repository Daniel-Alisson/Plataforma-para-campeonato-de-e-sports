package com.daniel.tde_backend.controllers;

import com.daniel.tde_backend.dto.PromocaoDTO;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import com.daniel.tde_backend.services.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/promocao")
public class PromocaoController {

    @Autowired
    private PromocaoService service;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<PromocaoDTO> solicitarPromocao() {
        String idJogador = emailAuthenticated();
        PromocaoDTO dto = service.solicitarPromocao(idJogador);
        return ResponseEntity.ok().body(dto);
    }

    // CRIAR DPS UMA VERIFICACAO PARA O CASO DO USUARIO SER PROMOTOR
    private String emailAuthenticated() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = (Usuario) usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            return usuario.getId();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter o email do usuário autenticado");
        }
    }
}
