package com.example.EstudosSpring.Missoes;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {
    public MissoesModel map(MissoesDTO missoesDTO){
        return new MissoesModel(
          missoesDTO.getId(),
          missoesDTO.getNome(),
          missoesDTO.getDificuldade(),
          missoesDTO.getNinjas()
        );
    }

    public MissoesDTO map(MissoesModel missoesModel){
        return new MissoesDTO(
          missoesModel.getId(),
          missoesModel.getNome(),
          missoesModel.getDificuldade(),
          missoesModel.getNinjas()
        );
    }
}
