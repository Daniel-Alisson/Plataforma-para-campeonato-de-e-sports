package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
