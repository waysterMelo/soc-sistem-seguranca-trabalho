package com.ocupacional.soc.Repositories.SegurancaTrabalho;

import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgrRepository extends JpaRepository<PgrEntity, Long> {
    Page<PgrEntity> findByUnidadeOperacional_Empresa_Id(Long empresaId, Pageable pageable);
}