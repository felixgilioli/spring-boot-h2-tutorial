package br.com.felixgilioli.alunoservice.service;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import br.com.felixgilioli.alunoservice.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public record AlunoService(AlunoRepository alunoRepository) {

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
}
