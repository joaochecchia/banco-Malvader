package com.example.EstudosSpring.Ninja;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninja")
public class NinjaController {
    public NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/menu")
    public String menuNinjas(){
        return "Menu dos ninjas";
    }

    //buscar
    @GetMapping("/mostrar")
    public List<NinjaDTO> mostrarTodosOsNinjas(){
        return ninjaService.listarNinjas();
    }

    //adicionar
    @PostMapping("/criar")
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja){
        return ninjaService.criarNinja(ninja);
    }

    @GetMapping("/buscar/{id}")
    public NinjaDTO buscarNinjaId(@PathVariable Long id){
        return ninjaService.listarNinjaPorId(id);
    }

    //editar
    @PutMapping("/editar/{id}")
    public NinjaDTO editarNinja(@PathVariable Long id,@RequestBody NinjaDTO atualizarNinja){
        return ninjaService.editarNinja(id, atualizarNinja);
    }

    //deletar
    @DeleteMapping("/deletar/{id}")
    public void deletarNinja(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
    }
}
