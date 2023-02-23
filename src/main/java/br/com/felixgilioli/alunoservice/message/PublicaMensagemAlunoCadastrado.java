package br.com.felixgilioli.alunoservice.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PublicaMensagemAlunoCadastrado {

    private static final Logger log = LoggerFactory.getLogger(PublicaMensagemAlunoCadastrado.class);

    public void publica(AlunoCadastrado alunoCadastrado) {
        log.info("publica mensagem de aluno cadastrado");
    }
}
