package com.example.EstudosSpring.Missoes;

import com.example.EstudosSpring.Ninja.NinjaDTO;
import com.example.EstudosSpring.Ninja.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissoesService {
    MissoesRepository missoesRepository;
    MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> listarMissoes(){
        return missoesRepository.findAll()
                .stream()
                .map(missoesMapper::map)
                .toList();
    }

    public MissoesDTO criarNinja(MissoesDTO missoesDTO){
        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        missoesModel = missoesRepository.save(missoesModel);

        return missoesMapper.map(missoesModel);
    }

    public MissoesDTO buscarMissoesPorId(Long id){
        return missoesRepository.findById(id)
                .map(missoesMapper::map)
                .orElse(null);
    }

    public MissoesDTO editarMissao(Long id, MissoesDTO missaoDTO){
        MissoesModel missao = missoesMapper.map(missaoDTO);
        missao.setId(id); // Garantir que o ID é mantido na atualização
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
    }

    public void DeletarMissoes(Long id){
        missoesRepository.deleteById(id);
    }
}
