package br.com.felixgilioli.alunoservice.service;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import br.com.felixgilioli.alunoservice.repository.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public record AlunoService(AlunoRepository alunoRepository) {

    private static final Logger log = LoggerFactory.getLogger(AlunoService.class);

    public Aluno salvar(Aluno aluno) {
        log.info("buscando aluno no banco de dados");
        return alunoRepository.save(aluno);
    }
}
