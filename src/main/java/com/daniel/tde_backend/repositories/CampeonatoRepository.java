package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Campeonato;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampeonatoRepository extends MongoRepository<Campeonato, String> {
}
