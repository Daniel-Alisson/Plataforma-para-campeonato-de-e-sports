package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.UsuarioDTO;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(String id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new UsuarioDTO(entity);
    }

    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setNickName(dto.getNickName());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setImgUrl(dto.getImgUrl());
        entity.setStatus(dto.getStatus());
    }
}
