package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.SetorEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SetorRepository extends JpaRepository<SetorEntity, Long> {

    Optional<SetorEntity> findByNomeAndEmpresaId(String nome, Long empresaId);

    List<SetorEntity> findByEmpresaId(Long empresaId);

}
