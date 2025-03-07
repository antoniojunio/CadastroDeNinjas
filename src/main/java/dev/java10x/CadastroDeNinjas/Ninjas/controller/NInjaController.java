package dev.java10x.CadastroDeNinjas.Ninjas.controller;

import dev.java10x.CadastroDeNinjas.Ninjas.model.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.service.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/ninjas")
public class NInjaController {

    private NinjaService ninjaService;

    public NInjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Bem vindo ao cadastro de ninjas";
    }

    @PostMapping("/criar")
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja) {
        return ninjaService.criarNinja(ninja);
    }

    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinjasPorId(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado) {
        return ninjaService.atualizarNinja(id, ninjaAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarNinjasPorId(@PathVariable Long id) {
        ninjaService.deletarNinjasPorId(id);
    }
}
