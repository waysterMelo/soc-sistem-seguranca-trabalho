package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.AgenteNocivoCatalogoResponseDTO;
import com.ocupacional.soc.Mapper.Cadastros.AgenteNocivoCatalogoMapper;
import com.ocupacional.soc.Repositories.Cadastros.AgenteNocivoCatalogoRepository;
import com.ocupacional.soc.Services.Cadastros.AgenteNocivo;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgenteNocivoImpl implements AgenteNocivo{

    private final AgenteNocivoCatalogoRepository repository;
    private final AgenteNocivoCatalogoMapper mapper;

    @Override
    public Page<AgenteNocivoCatalogoResponseDTO> listarAgentes(Pageable pageable) {
       return repository.findAll(pageable).map(mapper::toResponseDto);
    }

    @Transactional
    public Page<AgenteNocivoCatalogoResponseDTO> buscarPorDescricao(String descricao, Pageable pageable){
        return repository.findByDescricaoContainingIgnoreCase(descricao, pageable).map(mapper::toResponseDto);
    }

    @Transactional()
    public Page<AgenteNocivoCatalogoResponseDTO> buscarPorCodigo(
            String codigo, Pageable pageable) {

        log.debug("Buscando agentes nocivos por código: '{}' - página: {}, tamanho: {}",
                codigo, pageable.getPageNumber(), pageable.getPageSize());

        return repository.findByCodigoEsocial(codigo, pageable).map(mapper::toResponseDto);
    }

    @Transactional()
    public Page<AgenteNocivoCatalogoResponseDTO> buscarComFiltros(
            String descricao, String codigo, Pageable pageable) {

        log.debug("Buscando agentes nocivos com filtros - descrição: '{}', código: '{}', página: {}, tamanho: {}",
                descricao, codigo, pageable.getPageNumber(), pageable.getPageSize());

        // Se nenhum filtro foi informado, retorna todos
        if ((descricao == null || descricao.trim().isEmpty()) &&
                (codigo == null || codigo.trim().isEmpty())) {
            return listarAgentes(pageable);
        }

        return repository.findByDescricaoOrCodigo(descricao, codigo, pageable).map(mapper::toResponseDto);
    }


}
