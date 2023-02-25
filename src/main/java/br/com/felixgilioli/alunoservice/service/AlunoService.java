package br.com.felixgilioli.alunoservice.service;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import br.com.felixgilioli.alunoservice.exception.AlunoJaCadastradoException;
import br.com.felixgilioli.alunoservice.message.AlunoCadastrado;
import br.com.felixgilioli.alunoservice.message.PublicaMensagemAlunoCadastrado;
import br.com.felixgilioli.alunoservice.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public record AlunoService(AlunoRepository alunoRepository,
                           PublicaMensagemAlunoCadastrado publicaMensagemAlunoCadastrado) {

    public Aluno salvar(Aluno aluno) {
        boolean alunoJaExiste = alunoRepository.existsByEmail(aluno.getEmail());

        if (alunoJaExiste) {
            throw new AlunoJaCadastradoException("Já existe um aluno com esse email: " + aluno.getEmail());
        }

        aluno = alunoRepository.save(aluno);

        var alunoCadastrado = new AlunoCadastrado(aluno.getId(), aluno.getEmail());
        publicaMensagemAlunoCadastrado.publica(alunoCadastrado);

        return aluno;
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }
}
