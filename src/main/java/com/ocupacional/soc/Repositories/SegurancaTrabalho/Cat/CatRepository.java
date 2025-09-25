package com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Entities.Cat.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<CatEntity, Long> {
    List<CatEntity> findByAcidentadoId(Long funcionarioId);
}