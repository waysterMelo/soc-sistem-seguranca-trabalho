package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExameCatalogoRepository extends JpaRepository<ExameCatalogoEntity, Long>{
    boolean existsByCodigoExame(String codigoExame);
    Optional<ExameCatalogoEntity> findByCodigoExame(String codigoExame);

    // Para busca paginada
    Page<ExameCatalogoEntity> findByNomeExameContainingIgnoreCaseOrCodigoExameContainingIgnoreCase(String nomeExame, String codigoExame, Pageable pageable);

    // Para listar todos com filtro (sem paginação)
    List<ExameCatalogoEntity> findByNomeExameContainingIgnoreCaseOrCodigoExameContainingIgnoreCase(String nomeExame, String codigoExame);
}
