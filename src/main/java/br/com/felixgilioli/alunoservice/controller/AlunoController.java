package br.com.felixgilioli.alunoservice.controller;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import br.com.felixgilioli.alunoservice.service.AlunoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public record AlunoController(AlunoService alunoService) {

    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }
}
