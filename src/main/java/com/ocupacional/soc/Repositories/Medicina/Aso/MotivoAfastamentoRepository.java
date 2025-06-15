package com.ocupacional.soc.Repositories.Medicina.Aso;

import com.ocupacional.soc.Entities.Medicina.Aso.MotivoAfastamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotivoAfastamentoRepository extends JpaRepository<MotivoAfastamentoEntity, Long> {
    Optional<MotivoAfastamentoEntity> findByCodigo(String codigo);

    Page<MotivoAfastamentoEntity> findByDescricaoContainingIgnoreCaseOrCodigoContainingIgnoreCase(String descricao, String codigo, Pageable pageable);
}
