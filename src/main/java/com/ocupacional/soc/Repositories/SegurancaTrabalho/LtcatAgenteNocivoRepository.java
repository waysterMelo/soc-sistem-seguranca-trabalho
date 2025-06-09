package com.ocupacional.soc.Repositories.SegurancaTrabalho;

import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatAgenteNocivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LtcatAgenteNocivoRepository extends JpaRepository<LtcatAgenteNocivoEntity, Long> {
}