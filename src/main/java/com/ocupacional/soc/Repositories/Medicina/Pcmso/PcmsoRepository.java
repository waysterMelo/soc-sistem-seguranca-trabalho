package com.ocupacional.soc.Repositories.Medicina.Pcmso;

import com.ocupacional.soc.Entities.Medicina.Pcmso.PcmsoEntity;
import com.ocupacional.soc.Enuns.Medicina.Pcmso.PcmsoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PcmsoRepository extends JpaRepository<PcmsoEntity, Long> {

    @Query("SELECT p FROM PcmsoEntity p JOIN p.unidadeOperacional u JOIN u.empresa e " +
            "WHERE LOWER(e.nomeFantasia) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<PcmsoEntity> findBySearchTerm(@Param("search") String search, Pageable pageable);

    boolean existsByUnidadeOperacionalIdAndStatus(Long unidadeOperacionalId, PcmsoStatus status);

    boolean existsByUnidadeOperacionalIdAndStatusAndIdNot(Long unidadeOperacionalId, PcmsoStatus status, Long id);

}