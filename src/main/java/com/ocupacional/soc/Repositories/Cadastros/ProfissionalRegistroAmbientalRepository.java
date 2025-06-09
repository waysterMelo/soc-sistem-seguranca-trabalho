package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistroAmbientalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRegistroAmbientalRepository extends JpaRepository<ProfissionalRegistroAmbientalEntity, Long> {
}