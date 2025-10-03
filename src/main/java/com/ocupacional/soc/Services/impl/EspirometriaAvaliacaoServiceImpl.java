package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.ContextoProfissionalResponseDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoListDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoResponseDTO;
import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.Medicina.Espirometria.EspirometriaAvaliacaoEntity;
import com.ocupacional.soc.Enuns.Medicina.Espirometria.ConclusaoEspirometria;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.Espirometria.EspirometriaAvaliacaoMapper;
import com.ocupacional.soc.Repositories.Aparelhos.AparelhoRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncionarioRepository;
import com.ocupacional.soc.Repositories.Cadastros.ProfissionalRegistrosRepository;
import com.ocupacional.soc.Repositories.Medicina.Espirometria.EspirometriaAvaliacaoRepository;
import com.ocupacional.soc.Services.Medicina.Espirometria.EspirometriaAvaliacaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class EspirometriaAvaliacaoServiceImpl implements EspirometriaAvaliacaoService {

    private final EspirometriaAvaliacaoRepository repository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProfissionalRegistrosRepository registroProfissionalRepository;
    private final AparelhoRepository aparelhoRepository;
    private final EspirometriaAvaliacaoMapper mapper;

    @Override
    @Transactional
    public EspirometriaAvaliacaoResponseDTO create(EspirometriaAvaliacaoRequestDTO dto) {
        EspirometriaAvaliacaoEntity entity = new EspirometriaAvaliacaoEntity();
        mapToEntity(entity, dto);
        EspirometriaAvaliacaoEntity savedEntity = repository.save(entity);
        return buildResponseDtoWithRatio(savedEntity);
    }

    @Override
    @Transactional
    public EspirometriaAvaliacaoResponseDTO update(Long id, EspirometriaAvaliacaoRequestDTO dto) {
        EspirometriaAvaliacaoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação de Espirometria não encontrada com ID: " + id));
        mapToEntity(entity, dto);
        EspirometriaAvaliacaoEntity updatedEntity = repository.save(entity);
        return buildResponseDtoWithRatio(updatedEntity);
    }

    @Override
    @Transactional
    public Page<EspirometriaAvaliacaoListDTO> findAll(Pageable pageable, Long empresaId, Long setorId, String search) {
        String searchTerm = (search == null || search.isBlank()) ? "" : search;
        Page<EspirometriaAvaliacaoEntity> page = repository.findWithFilters(empresaId, setorId, searchTerm, pageable);
        return page.map(mapper::toListDto);
    }

    @Override
    @Transactional
    public EspirometriaAvaliacaoResponseDTO findById(Long id) {
        EspirometriaAvaliacaoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação de Espirometria não encontrada com ID: " + id));
        return buildResponseDtoWithRatio(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Primeiro, verificamos se o registro existe para evitar exceções desnecessárias
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Avaliação de Espirometria não encontrada com ID: " + id);
        }
        // Se houvesse uma imagem associada, aqui seria o local para chamar o fileStorageService.deleteFile()
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public ContextoProfissionalResponseDTO findContextoProfissional(Long funcionarioId) {
        ProfissionalRegistrosEntity registro = registroProfissionalRepository.findTopByFuncionarioIdOrderByIdDesc(funcionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro profissional encontrado para o funcionário com ID: " + funcionarioId));

        return ContextoProfissionalResponseDTO.builder()
                .nomeFuncionario(registro.getFuncionario().getNome())
                .nomeEmpresa(registro.getFuncao().getEmpresa().getNomeFantasia())
                .nomeSetor(registro.getFuncao().getSetor().getNome())
                .nomeFuncao(registro.getFuncao().getNome())
                .dataAdmissao(registro.getFuncionario().getDataAdmissao())
                .dataEntradaCargo(registro.getDataEntradaCargo())
                .build();
    }

    private void mapToEntity(EspirometriaAvaliacaoEntity entity, EspirometriaAvaliacaoRequestDTO dto) {
        // Validação da conclusão
        if (dto.getConclusao() == ConclusaoEspirometria.NORMAL && dto.getOutraConclusao() != null && !dto.getOutraConclusao().isBlank()) {
            throw new BusinessException("O campo 'Outra Conclusão' só pode ser preenchido se a conclusão for 'OUTROS'.");
        }
        if (dto.getConclusao() == ConclusaoEspirometria.OUTROS && (dto.getOutraConclusao() == null || dto.getOutraConclusao().isBlank())) {
            throw new BusinessException("O campo 'Outra Conclusão' é obrigatório quando a conclusão é 'OUTROS'.");
        }

        FuncionarioEntity funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + dto.getFuncionarioId()));

        entity.setFuncionario(funcionario);
        entity.setDataExame(dto.getDataExame());
        entity.setTipoExame(dto.getTipoExame());
        entity.setAltura(dto.getAltura());
        entity.setPeso(dto.getPeso());
        entity.setPef(dto.getPef());
        entity.setFev1(dto.getFev1());
        entity.setFvc(dto.getFvc());
        entity.setConclusao(dto.getConclusao());
        entity.setOutraConclusao(dto.getOutraConclusao());

        if (dto.getAparelhoUtilizadoId() != null) {
            AparelhoEntity aparelho = aparelhoRepository.findById(dto.getAparelhoUtilizadoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Aparelho não encontrado com ID: " + dto.getAparelhoUtilizadoId()));
            entity.setAparelhoUtilizado(aparelho);
        } else {
            entity.setAparelhoUtilizado(null);
        }
    }

    private EspirometriaAvaliacaoResponseDTO buildResponseDtoWithRatio(EspirometriaAvaliacaoEntity entity) {
        EspirometriaAvaliacaoResponseDTO dto = mapper.toResponseDto(entity);
        if (entity.getFvc() != null && entity.getFev1() != null && entity.getFvc().compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal ratio = entity.getFev1().divide(entity.getFvc(), 4, RoundingMode.HALF_UP);
            dto.setRelacaoFev1Fvc(ratio);
        }
        return dto;
    }
}