package com.gestao.escolar.model;

import com.gestao.escolar.model.turma.Turma;
import jakarta.persistence.*;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private Boolean necessidadeEspecial;
    private Boolean possuiLaudo;
    private Integer codigoCid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="turma" )
    private Turma turma;

    public Aluno() {
    }

    public Aluno(String nome, Integer idade, Boolean necessidadeEspecial, Boolean possuiLaudo, Integer codigoCid) {
        this.nome = nome;
        this.idade = idade;
        this.necessidadeEspecial = necessidadeEspecial;
        this.possuiLaudo = possuiLaudo;
        this.codigoCid = codigoCid;
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
    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Boolean getNecessidadeEspecial() {
        return necessidadeEspecial;
    }

    public void setNecessidadeEspecial(Boolean necessidadeEspecial) {
        this.necessidadeEspecial = necessidadeEspecial;
    }

    public Boolean getPossuiLaudo() {
        return possuiLaudo;
    }

    public void setPossuiLaudo(Boolean possuiLaudo) {
        this.possuiLaudo = possuiLaudo;
    }

    public Integer getCodigoCid() {
        return codigoCid;
    }

    public void setCodigoCid(Integer codigoCid) {
        this.codigoCid = codigoCid;
    }

    public Turma getTurma() {
        return turma;
    }
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}
