package br.com.felixgilioli.alunoservice.event.subscriber;

import br.com.felixgilioli.alunoservice.event.AlunoCadastradoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnviarEmailSubscriber {

    @EventListener
    public void enviaEmail(AlunoCadastradoEvent event) {
        System.out.println("enviando email...");
        throw new RuntimeException();
    }
}
