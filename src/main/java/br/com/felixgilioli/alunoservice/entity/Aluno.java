package br.com.felixgilioli.alunoservice.entity;

import br.com.felixgilioli.alunoservice.enumeration.Sexo;
import jakarta.persistence.*;

@Entity
public class Aluno {

    @Id
    @GeneratedValue
    private Long id;

    private String nomeCompleto;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
