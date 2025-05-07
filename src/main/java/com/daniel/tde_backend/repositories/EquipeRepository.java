package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Equipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipeRepository extends MongoRepository<Equipe, String> {
}
