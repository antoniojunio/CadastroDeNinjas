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

    @PutMapping("/alterarID")
    public String alterarNinjasPorId() {
        return "Alterar ninjas por Id";
    }

    @DeleteMapping("/deletarID")
    public String deletarNinjasPorId() {
        return "Deletar ninjas por Id";
    }
}
