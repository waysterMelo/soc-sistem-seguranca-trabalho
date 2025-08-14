package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameCatalogoRepository extends JpaRepository<ExameCatalogoEntity, Long>{

    @Query("select e from ExameCatalogoEntity e where lower(e.nomeExame) like lower(concat('%', :nome,'%'))")
    Page<ExameCatalogoEntity> findByNomeExame(@Param("nome") String nome, Pageable pageable);

}
