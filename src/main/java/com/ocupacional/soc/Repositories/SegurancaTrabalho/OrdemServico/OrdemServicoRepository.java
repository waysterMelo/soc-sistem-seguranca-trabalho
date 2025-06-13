package com.ocupacional.soc.Repositories.SegurancaTrabalho.OrdemServico;

import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.OrdemServicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity, Long> {

    @Query("SELECT os FROM OrdemServicoEntity os " +
            "JOIN os.funcionario f " +
            "JOIN os.funcao fun " +
            "WHERE (:funcionarioId IS NULL OR f.id = :funcionarioId) " +
            "AND (:search IS NULL OR LOWER(f.nome) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(fun.nome) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<OrdemServicoEntity> findWithFilters(
            @Param("funcionarioId") Long funcionarioId,
            @Param("search") String search,
            Pageable pageable);
}