package com.ocupacional.soc.Repositories.SegurancaTrabalho;

import com.ocupacional.soc.Entities.SegurancaTrabalho.LtipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LtipRepository extends JpaRepository<LtipEntity, Long> {

    @Query("SELECT l FROM LtipEntity l " +
            "WHERE (:empresaId IS NULL OR l.funcao.setor.empresa.id = :empresaId) " +
            "AND (:setorId IS NULL OR l.funcao.setor.id = :setorId) " +
            "AND (:funcaoId IS NULL OR l.funcao.id = :funcaoId)")
    Page<LtipEntity> findWithFilters(
            @Param("empresaId") Long empresaId,
            @Param("setorId") Long setorId,
            @Param("funcaoId") Long funcaoId,
            Pageable pageable);
}