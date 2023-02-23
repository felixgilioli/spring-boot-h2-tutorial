package br.com.felixgilioli.alunoservice.repository;

import br.com.felixgilioli.alunoservice.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByEmail(String email);
}
