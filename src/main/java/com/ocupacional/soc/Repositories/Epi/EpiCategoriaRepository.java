package com.ocupacional.soc.Repositories.Epi;

import org.springframework.stereotype.Repository;

import com.ocupacional.soc.Entities.Epi.EpiCategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EpiCategoriaRepository extends JpaRepository<EpiCategoriaEntity, Long> {


}