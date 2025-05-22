package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<UserDetails> findByEmail(String email);
}

/* USUARIOS PARA TESTES
    ADMIN
EMAIL: ADMIN@ADMIN
SENHA: 1Admin1!

    JOGADOR
EMAIL: jogador@gmail.com
SENHA: Jogador1!

    PROMOTOR
EMAIL: promotor@gmail.com
SENHA: Promotor1!

 */

