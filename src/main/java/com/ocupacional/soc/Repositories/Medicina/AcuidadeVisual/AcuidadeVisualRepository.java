package com.ocupacional.soc.Repositories.Medicina.AcuidadeVisual;

import com.ocupacional.soc.Entities.Medicina.AcuidadeVisual.AcuidadeVisualEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcuidadeVisualRepository extends JpaRepository<AcuidadeVisualEntity, Long> {

    @Query("SELECT av FROM AcuidadeVisualEntity av " +
            "JOIN av.registroProfissional rp " +
            "JOIN rp.funcionario f " +
            "WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(f.cpf) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<AcuidadeVisualEntity> findAllBySearchTerm(@Param("search") String search, Pageable pageable);
}