package br.com.felixgilioli.alunoservice.event.subscriber;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import br.com.felixgilioli.alunoservice.event.AlunoCadastradoEvent;
import br.com.felixgilioli.alunoservice.message.AlunoCadastrado;
import br.com.felixgilioli.alunoservice.message.PublicaMensagemAlunoCadastrado;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public record PublicaMensagemNaFilaSubscriber(PublicaMensagemAlunoCadastrado publicaMensagemAlunoCadastrado)
        implements ApplicationListener<AlunoCadastradoEvent> {

    @Override
    public void onApplicationEvent(AlunoCadastradoEvent event) {
        Aluno aluno = event.getAluno();
        var alunoCadastrado = new AlunoCadastrado(aluno.getId(), aluno.getEmail());
        publicaMensagemAlunoCadastrado.publica(alunoCadastrado);
    }
}
