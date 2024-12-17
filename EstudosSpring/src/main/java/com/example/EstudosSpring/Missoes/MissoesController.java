package com.example.EstudosSpring.Missoes;

import com.example.EstudosSpring.Ninja.NinjaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {
    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/menu")
    public String menuMissoes(){
        return "Menu das missoes";
    }

    //buscar
    @GetMapping("/mostrar")
    public List<MissoesDTO> mostrarTodasAsMissoes(){
        return missoesService.listarMissoes();
    }

    //adicionar
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissoes(@RequestBody MissoesDTO missao){
        MissoesDTO novoNinja = missoesService.criarNinja(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão com Id: " + novoNinja.getId() + " criado com sucesso!");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<String> buscarMissaoPorId(@PathVariable Long id){
        MissoesDTO missaoBuscada = missoesService.buscarMissoesPorId(id);
        if(missaoBuscada != null){
            return ResponseEntity.ok("Missao: " + missaoBuscada.getNome() + "!");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id: " + id + " não encontrado.");
        }
    }

    //editar
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarMissoes(@PathVariable Long id, @RequestBody MissoesDTO missaoEditada){
        if(missoesService.buscarMissoesPorId(id) != null){
            missoesService.editarMissao(id, missaoEditada);
            return ResponseEntity.ok("Missão com id: " + id + " alterada com sucesso!");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id " + id + " Não está cadastrado");
        }
    }

    //deletar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissoes(@PathVariable Long id){
        if(missoesService.buscarMissoesPorId(id) != null){
            missoesService.DeletarMissoes(id);

            return ResponseEntity.ok("Missão com id: " + id + " deletada com sucesso!");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id " + id + " Não está cadastrada");
        }
    }
}
