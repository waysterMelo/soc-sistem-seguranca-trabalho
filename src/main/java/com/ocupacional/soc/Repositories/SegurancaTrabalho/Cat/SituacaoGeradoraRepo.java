package com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Entities.Cat.SituacaoGeradoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoGeradoraRepo extends JpaRepository<SituacaoGeradoraEntity, Long> {
}
