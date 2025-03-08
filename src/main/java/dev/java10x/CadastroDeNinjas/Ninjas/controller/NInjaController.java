package dev.java10x.CadastroDeNinjas.Ninjas.controller;

import dev.java10x.CadastroDeNinjas.Ninjas.dto.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.service.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NInjaController {

    private NinjaService ninjaService;

    public NInjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da mensagem de boas-vindas para quem acessa ela.")
    public String boasVindas() {
        return "Bem vindo ao cadastro de ninjas";
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar um novo ninja", description = "Essa rota cria um novo ninja.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Erro na criação do ninja"),
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criada com sucesso: " + novoNinja.getNome() + " (ID)" + novoNinja.getId());
    }

    @GetMapping("/listar")
    @Operation
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por Id", description = "Rota lista um ninja pelo seu Id.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Ninja não encontrado"),
    })
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id: " + id + " não existe nos nossos registros!");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por Id", description = "Rota altera um ninja pelo Id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possivel alterar."),
    })
    public ResponseEntity<?> alterarNinjasPorId(
            @Parameter(description = "Id do ninja a ser alterado")
            @PathVariable Long id,
            @Parameter(description = "Dados do ninja a ser alterado")
            @RequestBody NinjaDTO ninjaAtualizado) {
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id: " + id + " não existe nos nossos registros!");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjasPorId(@PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjasPorId(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o ID " + id + "  não encontrado!");
        }
    }
}
