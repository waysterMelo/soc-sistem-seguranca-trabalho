package com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Entities.Cat.ParteCorpoAtingidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParteCorpoAtingidaRepo extends JpaRepository<ParteCorpoAtingidaEntity, Long> {
}
