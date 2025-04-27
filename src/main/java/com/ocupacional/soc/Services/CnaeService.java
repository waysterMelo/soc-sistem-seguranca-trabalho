package com.ocupacional.soc.Services;

import com.ocupacional.soc.Entities.CnaeEntity;
import com.ocupacional.soc.Repositories.CnaeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CnaeService {

    @Autowired
    private CnaeRepository cnaeRepository;

    public List<CnaeEntity> listarTodos() {
        return cnaeRepository.findAll();
    }

    public Optional<CnaeEntity> buscarPorId(Long id) {
        return cnaeRepository.findById(id);
    }

    public CnaeEntity salvar(CnaeEntity cnae) {
        return cnaeRepository.save(cnae);
    }

    public void deletar(Long id) {
        cnaeRepository.deleteById(id);
    }

    public CnaeEntity atualizar(Long id, CnaeEntity cnaeAtualizado) {
        return cnaeRepository.findById(id)
                .map(cnae -> {
                    cnae.setCodigo(cnaeAtualizado.getCodigo());
                    cnae.setDescricao(cnaeAtualizado.getDescricao());
                    return cnaeRepository.save(cnae);
                })
                .orElseThrow(() -> new RuntimeException("CNAE não encontrado"));
    }
} 