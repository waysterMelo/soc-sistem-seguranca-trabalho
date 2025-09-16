package com.ocupacional.soc.Repositories.SegurancaTrabalho;

import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LtcatRepository extends JpaRepository<LtcatEntity, Long> {
    boolean existsByFuncoes_Id(Long funcaoId);
    @Query("SELECT l FROM LtcatEntity l LEFT JOIN FETCH l.unidadeOperacional LEFT JOIN FETCH l.funcoes WHERE l.id = :id")
    Optional<LtcatEntity> findByIdWithDetails(@Param("id") Long id);
}