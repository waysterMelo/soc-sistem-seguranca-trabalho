package com.ocupacional.soc.Repositories.Epi;

import com.ocupacional.soc.Entities.Epi.EpiFabricanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiFabricanteRepository extends JpaRepository<EpiFabricanteEntity, Long> {
}