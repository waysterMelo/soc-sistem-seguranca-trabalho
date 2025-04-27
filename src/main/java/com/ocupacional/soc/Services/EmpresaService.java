package com.ocupacional.soc.Services;

import com.ocupacional.soc.Dto.EmpresaDto;
import com.ocupacional.soc.Entities.EmpresaEntity;
import com.ocupacional.soc.Repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<EmpresaEntity> listarTodas() {
        return empresaRepository.findAll();
    }

    public Optional<EmpresaEntity> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public EmpresaEntity salvar(EmpresaEntity empresa) {
        return empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }

    public EmpresaEntity atualizar(Long id, EmpresaEntity empresaAtualizada) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresa.setTipoEmpresa(empresaAtualizada.getTipoEmpresa());
                    empresa.setCpfOuCnpj(empresaAtualizada.getCpfOuCnpj());
                    empresa.setInscricaoEstadual(empresaAtualizada.getInscricaoEstadual());
                    empresa.setStatus(empresaAtualizada.getStatus());
                    empresa.setRazaoSocial(empresaAtualizada.getRazaoSocial());
                    empresa.setNomeFantasia(empresaAtualizada.getNomeFantasia());
                    empresa.setLogomarcaUrl(empresaAtualizada.getLogomarcaUrl());
                    empresa.setEndereco(empresaAtualizada.getEndereco());
                    empresa.setTelefones(empresaAtualizada.getTelefones());
                    empresa.setEmail(empresaAtualizada.getEmail());
                    empresa.setGrauRisco(empresaAtualizada.getGrauRisco());
                    empresa.setCnaePrincipal(empresaAtualizada.getCnaePrincipal());
                    empresa.setCnaesSecundarios(empresaAtualizada.getCnaesSecundarios());
                    empresa.setTipoMatrizFilial(empresaAtualizada.getTipoMatrizFilial());
                    empresa.setEmpresaMatriz(empresaAtualizada.getEmpresaMatriz());
                    empresa.setMedicoResponsavelPcmsso(empresaAtualizada.getMedicoResponsavelPcmsso());
                    empresa.setObservacoes(empresaAtualizada.getObservacoes());
                    return empresaRepository.save(empresa);
                })
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }
} 