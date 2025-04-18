package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    UserDetails findByEmail(String email);
}