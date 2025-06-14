package com.ocupacional.soc.Repositories.Medicina.PcmsoAnalitico;

import com.ocupacional.soc.Entities.Medicina.PcmsoAnalitico.PcmsoAnaliticoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PcmsoAnaliticoRepository extends JpaRepository<PcmsoAnaliticoEntity, Long> {

    // Métodos para checar unicidade
    boolean existsByEmpresaId(Long empresaId);
    boolean existsByUnidadeOperacionalId(Long unidadeOperacionalId);
    boolean existsByEmpresaIdAndIdNot(Long empresaId, Long id);
    boolean existsByUnidadeOperacionalIdAndIdNot(Long unidadeOperacionalId, Long id);

    @Query("SELECT pa FROM PcmsoAnaliticoEntity pa " +
            "LEFT JOIN pa.empresa e " +
            "LEFT JOIN pa.unidadeOperacional u " +
            "LEFT JOIN pa.medicoResponsavel m " +
            "WHERE LOWER(e.nomeFantasia) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(m.nome) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<PcmsoAnaliticoEntity> findBySearchTerm(@Param("search") String search, Pageable pageable);
}