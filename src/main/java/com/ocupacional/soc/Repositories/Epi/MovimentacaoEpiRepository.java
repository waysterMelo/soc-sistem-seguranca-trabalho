package com.ocupacional.soc.Repositories.Epi;


import com.ocupacional.soc.Entities.Epi.MovimentacaoEpiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimentacaoEpiRepository extends JpaRepository<MovimentacaoEpiEntity, Long> {

    @Query("SELECT m FROM MovimentacaoEpiEntity m JOIN FETCH m.funcionario JOIN FETCH m.epi WHERE m.funcionario.id = :funcionarioId")
    List<MovimentacaoEpiEntity> findByFuncionarioIdWithDetails(@Param("funcionarioId") Long funcionarioId);
}