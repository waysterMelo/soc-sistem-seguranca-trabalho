package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeOperacionalRepository  extends JpaRepository<UnidadeOperacionalEntity, Long> {

    List<UnidadeOperacionalEntity> findByEmpresaId(Long empresaId);
    Page<UnidadeOperacionalEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    boolean existsBySetores_Id(Long setorId);
}
