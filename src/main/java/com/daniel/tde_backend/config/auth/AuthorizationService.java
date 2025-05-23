package com.daniel.tde_backend.config.auth;

import com.daniel.tde_backend.exceptions.DatabaseException;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário inválido"));
    }

    public String getIdAuthenticated() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = (Usuario) repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            return usuario.getId();
        } catch (Exception e) {
            throw new DatabaseException("Erro ao obter o usuário autenticado");
        }
    }
}
