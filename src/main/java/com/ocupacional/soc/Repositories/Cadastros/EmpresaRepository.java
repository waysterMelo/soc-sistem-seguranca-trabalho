package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    @Query("SELECT e FROM EmpresaEntity e where lower(e.razaoSocial) like lower(concat('%', :termo, '%')) or " +
            "lower(e.nomeFantasia) like lower(concat('%', :termo, '%')) or " +
            "lower(e.cpfOuCnpj) like lower(concat('%', :termo, '%') )")
    Page<EmpresaEntity> buscarPorTermo(String termo, Pageable pageable);

}