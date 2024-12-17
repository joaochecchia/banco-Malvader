package com.example.EstudosSpring.Ninja;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {
    NinjaRepository ninjaRepository;
    NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDTO> listarNinjas(){
        return ninjaRepository.findAll()
                .stream()
                .map(ninjaMapper::map)
                .toList();
    }

    public NinjaDTO listarNinjaPorId(Long id){
        return ninjaMapper.map(ninjaRepository.findById(id).get());
    }

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    public NinjaDTO editarNinja(Long id ,NinjaDTO ninjaDTO){
        if(ninjaRepository.existsById(id)){
            NinjaModel ninja = ninjaMapper.map(ninjaDTO);
            ninja = ninjaRepository.save(ninja);
            return ninjaMapper.map(ninja);
        }
        return null;
    }

    public void deletarNinjaPorId(Long id){
        ninjaRepository.deleteById(id);
    }
}
