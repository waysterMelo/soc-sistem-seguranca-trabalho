package com.ocupacional.soc.Services.impl;


import com.ocupacional.soc.Dto.Cadastros.LaboratorioDTO;
import com.ocupacional.soc.Entities.Cadastros.EnderecoEntity;
import com.ocupacional.soc.Entities.Cadastros.LaboratorioEntity;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.EnderecoMapper;
import com.ocupacional.soc.Mapper.Cadastros.LaboratorioMapper;
import com.ocupacional.soc.Repositories.Cadastros.LaboratorioRepository;
import com.ocupacional.soc.Services.Cadastros.LaboratorioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LaboratorioServiceImpl implements LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;
    private final LaboratorioMapper laboratorioMapper;
    private final EnderecoMapper enderecoMapper;


    @Override
    @Transactional
    public Page<LaboratorioDTO> findAll(Pageable pageable, String searchTerm) {
        String query = (searchTerm == null || searchTerm.isBlank()) ? "" : searchTerm;
        Page<LaboratorioEntity> page = laboratorioRepository.findByNomeFantasiaContainingIgnoreCaseOrRazaoSocialContainingIgnoreCase(query, query, pageable);
        return page.map(laboratorioMapper::toDto);
    }

    @Override
    @Transactional
    public LaboratorioDTO findById(Long id) {
        LaboratorioEntity entity = laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado com ID: " + id));
        return laboratorioMapper.toDto(entity);
    }

    @Override
    @Transactional
    public LaboratorioDTO create(LaboratorioDTO dto) {
        if (dto.getCnpj() != null && !dto.getCnpj().isBlank()) {
            laboratorioRepository.findByCnpj(dto.getCnpj()).ifPresent(e -> {
                throw new BusinessException("Já existe um laboratório cadastrado com o CNPJ: " + dto.getCnpj());
            });
        }
        LaboratorioEntity entity = new LaboratorioEntity();
        mapDtoToEntity(dto, entity); // Mapeamento manual
        LaboratorioEntity savedEntity = laboratorioRepository.save(entity);
        return laboratorioMapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public LaboratorioDTO update(Long id, LaboratorioDTO dto) {
        LaboratorioEntity entity = laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado com ID: " + id));

        if (dto.getCnpj() != null && !dto.getCnpj().isBlank()) {
            laboratorioRepository.findByCnpj(dto.getCnpj()).ifPresent(e -> {
                if (!e.getId().equals(id)) {
                    throw new BusinessException("O CNPJ " + dto.getCnpj() + " já está em uso por outro laboratório.");
                }
            });
        }

        mapDtoToEntity(dto, entity); // Mapeamento manual
        LaboratorioEntity updatedEntity = laboratorioRepository.save(entity);
        return laboratorioMapper.toDto(updatedEntity);
    }

    private void mapDtoToEntity(LaboratorioDTO dto, LaboratorioEntity entity) {
        entity.setRazaoSocial(dto.getRazaoSocial());
        entity.setNomeFantasia(dto.getNomeFantasia());
        entity.setCnpj(dto.getCnpj());
        entity.setEmail(dto.getEmail());

        if (dto.getEndereco() != null) {
            EnderecoEntity endereco = enderecoMapper.toEntity(dto.getEndereco());
            entity.setEndereco(endereco);
        } else {
            entity.setEndereco(null);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!laboratorioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Laboratório não encontrado com ID: " + id);
        }
        // Adicionar aqui validação se o laboratório está em uso em algum Exame Toxicológico antes de deletar
        laboratorioRepository.deleteById(id);
    }
}