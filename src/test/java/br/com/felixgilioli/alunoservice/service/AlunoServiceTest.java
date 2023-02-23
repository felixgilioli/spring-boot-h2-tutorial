package br.com.felixgilioli.alunoservice.service;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import br.com.felixgilioli.alunoservice.enumeration.Sexo;
import br.com.felixgilioli.alunoservice.exception.AlunoJaCadastradoException;
import br.com.felixgilioli.alunoservice.message.AlunoCadastrado;
import br.com.felixgilioli.alunoservice.message.PublicaMensagemAlunoCadastrado;
import br.com.felixgilioli.alunoservice.repository.AlunoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private PublicaMensagemAlunoCadastrado publicaMensagemAlunoCadastrado;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    @DisplayName("Salvar quando já existe um aluno com o mesmo email cadastrado")
    void salvarQuandoJaExisteUmAlunoCadastrado() {
        var aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNomeCompleto("Felix Gilioli");
        aluno.setSexo(Sexo.M);
        aluno.setEmail("felix@email.com");

        when(alunoRepository.existsByEmail(aluno.getEmail())).thenReturn(true);

        var exception = assertThrows(AlunoJaCadastradoException.class, () -> alunoService.salvar(aluno));
        assertTrue(exception.getMessage().contains(aluno.getEmail()));

        verify(alunoRepository, never()).save(aluno);
        verify(publicaMensagemAlunoCadastrado, never()).publica(any());
    }

    @Test
    void salvarComSucesso() {
        var aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNomeCompleto("Felix Gilioli");
        aluno.setSexo(Sexo.M);
        aluno.setEmail("felix@email.com");

        when(alunoRepository.existsByEmail(aluno.getEmail())).thenReturn(false);
        when(alunoRepository.save(aluno)).thenReturn(aluno);
        doNothing().when(publicaMensagemAlunoCadastrado).publica(any());

        var alunoSalvo = alunoService.salvar(aluno);
        assertEquals(aluno.getId(), alunoSalvo.getId());

        verify(alunoRepository, times(1)).save(aluno);

        var alunoCadastradoCaptor = ArgumentCaptor.forClass(AlunoCadastrado.class);
        verify(publicaMensagemAlunoCadastrado, times(1)).publica(alunoCadastradoCaptor.capture());

        assertEquals(aluno.getId(), alunoCadastradoCaptor.getValue().id());
        assertEquals(aluno.getEmail(), alunoCadastradoCaptor.getValue().email());
    }
}
