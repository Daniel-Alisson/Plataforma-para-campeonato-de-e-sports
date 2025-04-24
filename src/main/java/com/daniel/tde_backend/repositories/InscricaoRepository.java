package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Inscricao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends MongoRepository<Inscricao, String> {

}
