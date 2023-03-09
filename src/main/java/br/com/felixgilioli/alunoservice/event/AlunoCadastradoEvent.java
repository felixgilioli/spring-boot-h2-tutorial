package br.com.felixgilioli.alunoservice.event;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import org.springframework.context.ApplicationEvent;

public class AlunoCadastradoEvent extends ApplicationEvent {

    private final Aluno aluno;

    public AlunoCadastradoEvent(Object source, Aluno aluno) {
        super(source);
        this.aluno = aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }
}
