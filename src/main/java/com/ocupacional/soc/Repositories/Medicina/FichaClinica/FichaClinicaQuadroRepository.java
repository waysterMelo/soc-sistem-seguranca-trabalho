package com.ocupacional.soc.Repositories.Medicina.FichaClinica;

import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaQuadroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaClinicaQuadroRepository extends JpaRepository<FichaClinicaQuadroEntity, Long> {
}
