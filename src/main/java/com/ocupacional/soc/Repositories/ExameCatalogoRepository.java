package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.ExamesPCMSO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameCatalogoRepository extends JpaRepository<ExamesPCMSO, Long>{




}
