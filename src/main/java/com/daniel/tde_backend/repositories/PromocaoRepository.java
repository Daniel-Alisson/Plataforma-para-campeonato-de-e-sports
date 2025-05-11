package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Promocao;
import com.daniel.tde_backend.models.enums.PromocaoStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PromocaoRepository extends MongoRepository<Promocao, String> {
    Optional<Promocao> findByIdJogadorAndStatus(String idJogador, PromocaoStatus status);
}
