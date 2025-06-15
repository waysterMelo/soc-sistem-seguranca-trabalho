package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaModeloListDTO;
import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaModeloRequestDTO;
import com.ocupacional.soc.Dto.Medicina.FichaClinica.FichaClinicaResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaModeloEntity;
import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaOpcaoPerguntaEntity;
import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaPerguntaEntity;
import com.ocupacional.soc.Entities.Medicina.Fichaclinica.FichaClinicaQuadroEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.Fichaclinica.FichaClinicaModeloMapper;
import com.ocupacional.soc.Repositories.Medicina.FichaClinica.FichaClinicaModeloRepository;
import com.ocupacional.soc.Services.Medicina.Fichaclinica.FichaClinicaModeloService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FichaClinicaModeloServiceImpl implements FichaClinicaModeloService {

    private final FichaClinicaModeloRepository modeloRepository;
    private final FichaClinicaModeloMapper fichaClinicaModeloMapper;

    @Override
    @Transactional
    public List<FichaClinicaModeloListDTO> findAll() {
        List<FichaClinicaModeloEntity> modelos = modeloRepository.findAll();
        return fichaClinicaModeloMapper.toListDto(modelos);
    }

    @Override
    @Transactional
    public FichaClinicaResponseDTO findById(Long id) {
        FichaClinicaModeloEntity modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modelo de Ficha Clínica não encontrado com ID: " + id));
        return fichaClinicaModeloMapper.toResponseDto(modelo);
    }

    @Override
    @Transactional
    public FichaClinicaResponseDTO create(FichaClinicaModeloRequestDTO dto) {
        FichaClinicaModeloEntity modeloEntity = new FichaClinicaModeloEntity();
        mapDtoToEntity(modeloEntity, dto);
        FichaClinicaModeloEntity savedEntity = modeloRepository.save(modeloEntity);
        return fichaClinicaModeloMapper.toResponseDto(savedEntity);
    }

    @Override
    @Transactional
    public FichaClinicaResponseDTO update(Long id, FichaClinicaModeloRequestDTO dto) {
        FichaClinicaModeloEntity modeloEntity = modeloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modelo de Ficha Clínica não encontrado com ID: " + id));

        // Estratégia "apagar e recriar" para os filhos, garantindo consistência.
        mapDtoToEntity(modeloEntity, dto);

        FichaClinicaModeloEntity updatedEntity = modeloRepository.save(modeloEntity);
        return fichaClinicaModeloMapper.toResponseDto(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!modeloRepository.existsById(id)) {
            throw new ResourceNotFoundException("Modelo de Ficha Clínica não encontrado com ID: " + id);
        }
        modeloRepository.deleteById(id);
    }

    // Método helper para mapear o DTO complexo para a hierarquia de entidades
    private FichaClinicaModeloEntity mapDtoToEntity(FichaClinicaModeloEntity modeloEntity, FichaClinicaModeloRequestDTO dto) {
        modeloEntity.setNome(dto.getNome());
        modeloEntity.setImprimirDuasColunas(dto.isImprimirDuasColunas());
        modeloEntity.setExibirCampoAssinatura(dto.isExibirCampoAssinatura());

        // Limpa a lista antiga para substituir pela nova (essencial para o update)
        modeloEntity.getQuadros().clear();

        if (dto.getQuadros() != null) {
            dto.getQuadros().forEach(quadroDto -> {
                FichaClinicaQuadroEntity quadroEntity = new FichaClinicaQuadroEntity();
                quadroEntity.setNome(quadroDto.getNome());
                quadroEntity.setObservacoes(quadroDto.getObservacoes());
                quadroEntity.setModelo(modeloEntity); // Associa o quadro ao modelo

                if (quadroDto.getPerguntas() != null) {
                    quadroDto.getPerguntas().forEach(perguntaDto -> {
                        FichaClinicaPerguntaEntity perguntaEntity = new FichaClinicaPerguntaEntity();
                        perguntaEntity.setNome(perguntaDto.getNome());
                        perguntaEntity.setTipo(perguntaDto.getTipo());
                        perguntaEntity.setQuadro(quadroEntity); // Associa a pergunta ao quadro

                        if (perguntaDto.getOpcoes() != null) {
                            perguntaDto.getOpcoes().forEach(opcaoDto -> {
                                FichaClinicaOpcaoPerguntaEntity opcaoEntity = new FichaClinicaOpcaoPerguntaEntity();
                                opcaoEntity.setTextoOpcao(opcaoDto.getTextoOpcao());
                                opcaoEntity.setPergunta(perguntaEntity); // Associa a opção à pergunta
                                perguntaEntity.getOpcoes().add(opcaoEntity);
                            });
                        }
                        quadroEntity.getPerguntas().add(perguntaEntity);
                    });
                }
                modeloEntity.getQuadros().add(quadroEntity);
            });
        }
        return modeloEntity;
    }
}