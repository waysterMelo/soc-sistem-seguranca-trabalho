package com.ocupacional.soc.Repositories.Medicina.Toxicologico;

import com.ocupacional.soc.Entities.Medicina.Toxicologico.ExameToxicologicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameToxicologicoRepository extends JpaRepository<ExameToxicologicoEntity, Long> {

    boolean existsByCodigoExame(String codigoExame);

    boolean existsByCodigoExameAndIdNot(String codigoExame, Long id);

    @Query("SELECT e FROM ExameToxicologicoEntity e " +
            "JOIN e.registroProfissional rp " +
            "JOIN rp.funcionario f " +
            "WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(f.cpf) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<ExameToxicologicoEntity> findAllBySearchTerm(@Param("search") String search, Pageable pageable);
}