package com.ocupacional.soc.Repositories.SegurancaTrabalho;

import com.ocupacional.soc.Entities.SegurancaTrabalho.PgrEntity;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PgrRepository extends JpaRepository<PgrEntity, Long> {
    Page<PgrEntity> findByUnidadeOperacional_Empresa_Id(Long empresaId, Pageable pageable);
    
    @Query("SELECT p FROM PgrEntity p WHERE p.unidadeOperacional.empresa.id = :empresaId " +
           "AND (:status IS NULL OR p.status = :status)")
    Page<PgrEntity> findByEmpresaIdAndStatus(@Param("empresaId") Long empresaId, 
                                            @Param("status") StatusEmpresa status, 
                                            Pageable pageable);
}