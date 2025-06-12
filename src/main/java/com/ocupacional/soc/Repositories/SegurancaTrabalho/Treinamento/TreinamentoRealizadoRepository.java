package com.ocupacional.soc.Repositories.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoRealizadoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinamentoRealizadoRepository extends JpaRepository<TreinamentoRealizadoEntity, Long> {

    @Query("select tr from TreinamentoRealizadoEntity tr left join tr.empresa e left join e.unidadesOperacionais u " +
            "where (:empresaId is null or e.id = :empresaId) and (:unidadeId is null or u.id = :unidadeId) group by tr.id")
    Page<TreinamentoRealizadoEntity> findWithFilters(
            @Param("empresaId") Long empresaId,
            @Param("unidadeId") Long unidadeId,
            Pageable pageable);
}
