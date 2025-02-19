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
    private Double peso;
    private Double altura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="turma" )
    private Turma turma;
    public Aluno() {
    }
    public Aluno(String nome, Integer idade, Double peso, Double altura) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
    }
    
    public Aluno(Double peso, Double altura) {
        this.peso = peso;
        this.altura = altura;
    }
    public Double calcIMC(){
        Double imc = null;
        if(peso != null && altura != null){
            imc = peso/(altura*altura);
        }
        return imc;
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
    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public Double getAltura() {
        return altura;
    }
    public void setAltura(Double altura) {
        this.altura = altura;
    }
    public Turma getTurma() {
        return turma;
    }
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}
