package com.ocupacional.soc.Repositories.Medicina.Espirometria;

import com.ocupacional.soc.Entities.Medicina.Espirometria.EspirometriaAvaliacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EspirometriaAvaliacaoRepository extends JpaRepository<EspirometriaAvaliacaoEntity, Long> {

    @Query("SELECT e FROM EspirometriaAvaliacaoEntity e JOIN e.funcionario f JOIN f.empresa emp " +
            "WHERE (:empresaId IS NULL OR emp.id = :empresaId) " +
            "AND (:setorId IS NULL OR f.setor.id = :setorId) " +
            "AND LOWER(f.nome) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<EspirometriaAvaliacaoEntity> findWithFilters(
            @Param("empresaId") Long empresaId,
            @Param("setorId") Long setorId,
            @Param("search") String search,
            Pageable pageable);
}