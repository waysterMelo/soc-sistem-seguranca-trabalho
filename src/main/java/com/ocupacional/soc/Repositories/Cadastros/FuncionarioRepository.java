package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    Optional<FuncionarioEntity> findByCpf(String cpf);

    Optional<FuncionarioEntity> findByMatricula(String matricula);

    Optional<FuncionarioEntity> findByEmail(String email);

    Page<FuncionarioEntity> findByEmpresaId(Long empresaId, Pageable pageable);
}