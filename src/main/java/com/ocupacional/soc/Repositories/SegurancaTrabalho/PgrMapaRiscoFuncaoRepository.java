package com.ocupacional.soc.Repositories.SegurancaTrabalho;

import com.ocupacional.soc.Entities.SegurancaTrabalho.Pgr.PgrMapaRiscoFuncaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgrMapaRiscoFuncaoRepository extends JpaRepository<PgrMapaRiscoFuncaoEntity, Long> {
}