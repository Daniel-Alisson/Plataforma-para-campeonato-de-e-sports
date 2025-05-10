package com.daniel.tde_backend.repositories;

import com.daniel.tde_backend.models.Campeonato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CampeonatoRepository extends MongoRepository<Campeonato, String> {
    @Query("{'idPromotor': ?0}")
    Page<Campeonato> findByPromotorId(String idPromotor, Pageable pageable);

}
