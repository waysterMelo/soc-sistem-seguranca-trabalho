package com.ocupacional.soc.Repositories.Medicina.FichaClinica;

import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaModeloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaClinicaModeloRepository extends JpaRepository<FichaClinicaModeloEntity, Long> {
}