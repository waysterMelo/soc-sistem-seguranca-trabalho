package com.ocupacional.soc.Repositories.Medicina.Aso;

import com.ocupacional.soc.Entities.Medicina.Aso.AsoExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsoExameRepository extends JpaRepository<AsoExameEntity, Long> {
}