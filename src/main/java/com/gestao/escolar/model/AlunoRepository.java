package com.gestao.escolar.model;

import com.gestao.escolar.model.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
    
    @Query("SELECT a FROM Aluno a WHERE a.turma is null")
    public List<Aluno> getAlunosSemTurma();
    public List<Aluno> findByTurmaIsNull();

    @Query("SELECT a FROM Aluno a WHERE a.turma = :e")
    public List<Aluno> getAlunosPorTurma(@Param("e") Turma e);
    // public List<Aluno> findByTurma(Turma e);

}
