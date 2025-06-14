package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfissionalRegistrosRepository extends JpaRepository<ProfissionalRegistrosEntity, Long> {
    Optional<ProfissionalRegistrosEntity> findTopByFuncionarioIdOrderByIdDesc(Long funcionarioId);

}