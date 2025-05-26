package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Partida;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PartidaRepository extends MongoRepository<Partida, String> {
    List<Partida> findByParticipantesContains(String idParticipante);
    List<Partida> findByIdCampeonato(String idCampeonato);
}
