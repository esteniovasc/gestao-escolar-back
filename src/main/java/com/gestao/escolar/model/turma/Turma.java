package com.gestao.escolar.model.turma;

import com.gestao.escolar.model.Aluno;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String local;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<Aluno> alunos;

    public Turma() {
    }

    public Turma(Long id, String nome, String local, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.alunos = alunos;
    }

    public Turma(String nome, String local, List<Aluno> alunos) {
        this.nome = nome;
        this.local = local;
        this.alunos = alunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Turma [getId()=" + getId() + ", getNome()=" + getNome() + ", getLocal()=" + getLocal()
                + ", getAlunos()=" + getAlunos() + "]";
    }
}
