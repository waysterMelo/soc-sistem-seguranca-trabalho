package com.ocupacional.soc.Repositories.Epi;

import com.ocupacional.soc.Entities.Epi.EpiEpcEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiEpcRepository extends JpaRepository<EpiEpcEntity, Long> {

    Page<EpiEpcEntity> findByEmpresaIdAndNomeContainingIgnoreCase(Long empresaId, String nome, Pageable pageable);

    Page<EpiEpcEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
