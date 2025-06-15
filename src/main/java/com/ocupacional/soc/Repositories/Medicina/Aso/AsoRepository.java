package com.ocupacional.soc.Repositories.Medicina.Aso;

import com.ocupacional.soc.Entities.Medicina.Aso.AsoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AsoRepository extends JpaRepository<AsoEntity, Long> {



    @Query("SELECT a FROM AsoEntity a JOIN a.registroProfissional rp JOIN rp.funcionario f JOIN f.empresa emp " +
            "WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(emp.nomeFantasia) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<AsoEntity> findBySearchTerm(@Param("search") String search, Pageable pageable);
}