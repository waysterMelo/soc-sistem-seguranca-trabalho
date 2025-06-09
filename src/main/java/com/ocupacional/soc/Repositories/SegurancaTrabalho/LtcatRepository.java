package com.ocupacional.soc.Repositories.SegurancaTrabalho;

import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LtcatRepository extends JpaRepository<LtcatEntity, Long> {
}