package com.ocupacional.soc.Repositories.Medicina.Aso;


import com.ocupacional.soc.Entities.Medicina.Aso.AfastamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AfastamentoRepository extends JpaRepository<AfastamentoEntity, Long> {

    @Query("SELECT a FROM AfastamentoEntity a " +
            "JOIN a.registroProfissional rp " +
            "JOIN rp.funcionario f " +
            "JOIN f.empresa emp " +
            "WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(emp.nomeFantasia) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<AfastamentoEntity> findBySearchTerm(@Param("search") String search, Pageable pageable);
}