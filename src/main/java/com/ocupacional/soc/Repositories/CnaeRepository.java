package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.CnaeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnaeRepository extends JpaRepository<CnaeEntity, Long> {
} 