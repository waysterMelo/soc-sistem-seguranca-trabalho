package com.ocupacional.soc.Repositories.PrestadorServico;

import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestadorServicoRepository extends JpaRepository<PrestadorServicoEntity, Long> {

    Optional<PrestadorServicoEntity> findByCpf(String cpf);

    Page<PrestadorServicoEntity> findByNomeContainingIgnoreCaseOrCpfContaining(
            String nome, String cpf, Pageable pageable);

}
