package br.com.felixgilioli.alunoservice.service;

import br.com.felixgilioli.alunoservice.client.viacep.ViacepClient;
import br.com.felixgilioli.alunoservice.entity.Aluno;
import br.com.felixgilioli.alunoservice.exception.AlunoJaCadastradoException;
import br.com.felixgilioli.alunoservice.message.AlunoCadastrado;
import br.com.felixgilioli.alunoservice.message.PublicaMensagemAlunoCadastrado;
import br.com.felixgilioli.alunoservice.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public record AlunoService(AlunoRepository alunoRepository,
                           PublicaMensagemAlunoCadastrado publicaMensagemAlunoCadastrado,
                           ViacepClient viacepClient) {

    public Aluno salvar(Aluno aluno) {
        boolean alunoJaExiste = alunoRepository.existsByEmail(aluno.getEmail());

        if (alunoJaExiste) {
            throw new AlunoJaCadastradoException("Já existe um aluno com esse email: " + aluno.getEmail());
        }

        if (aluno.getCep() != null) {
            var endereco = viacepClient.getEndereco(aluno.getCep());

            if (endereco != null) {
                aluno.setEstado(endereco.uf());
                aluno.setCidade(endereco.localidade());
                aluno.setBairro(endereco.bairro());
                aluno.setRua(endereco.logradouro());
            }
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
