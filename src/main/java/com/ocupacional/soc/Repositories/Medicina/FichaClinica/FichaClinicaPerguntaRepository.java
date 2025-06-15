package com.ocupacional.soc.Repositories.Medicina.FichaClinica;

import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaPerguntaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaClinicaPerguntaRepository extends JpaRepository<FichaClinicaPerguntaEntity, Long> {
}
