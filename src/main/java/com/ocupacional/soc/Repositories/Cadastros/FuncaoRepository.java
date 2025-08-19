package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncaoRepository extends JpaRepository<FuncaoEntity, Long> {
    Page<FuncaoEntity> findAllBySetorId(Long setorId, Pageable pageable);
    boolean existsBySetorId(Long setorId);
}
