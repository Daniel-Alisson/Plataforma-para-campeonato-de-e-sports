package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.UsuarioCadastroDTO;
import com.daniel.tde_backend.dto.UsuarioDTO;
import com.daniel.tde_backend.exceptions.InvalidDataException;
import com.daniel.tde_backend.exceptions.InvalidEmailException;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.models.enums.UsuarioTipo;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDTO cadastrarUsuario(UsuarioCadastroDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new InvalidEmailException("E-mail já cadastro");
        }
        Usuario entity = new Usuario();
        entity.setEmail(dto.getEmail());
        entity.setSenha(passwordEncoder.encode(dto.getSenha()));
        entity.setTipo(UsuarioTipo.JOGADOR);
        entity = repository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(String id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UsuarioDTO(usuario);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> result = repository.findAll(pageable);
        return result.map(x -> new UsuarioDTO(x));
    }

    // Testar idade
    @Transactional
    public UsuarioDTO update(String id, UsuarioDTO dto) {
        if (dto.getDataNascimento().isAfter(LocalDate.now().minusYears(13))) {
            throw new InvalidDataException("Usuário deve ter pelo menos 13 anos");
        }
        Usuario entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(String id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setNickName(dto.getNickName());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setImgUrl(dto.getImgUrl());
        entity.setTipo(dto.getTipo());
    }
}