package br.com.felixgilioli.alunoservice.controller;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import br.com.felixgilioli.alunoservice.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public record AlunoController(AlunoService alunoService) {

    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @Operation(description = "Busca o aluno pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o aluno"),
            @ApiResponse(responseCode = "400", description = "Não existe o aluno com o id informado")
    })
    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }
}
