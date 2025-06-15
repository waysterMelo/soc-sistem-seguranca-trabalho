package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.LaboratorioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, Long> {

    Optional<LaboratorioEntity> findByCnpj(String cnpj);

    Page<LaboratorioEntity> findByNomeFantasiaContainingIgnoreCaseOrRazaoSocialContainingIgnoreCase(
            String nomeFantasia, String razaoSocial, Pageable pageable);
}