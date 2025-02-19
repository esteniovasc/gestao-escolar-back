package com.gestao.escolar.controller;

import com.gestao.escolar.model.Aluno;
import com.gestao.escolar.model.AlunoRepository;
import com.gestao.escolar.model.turma.Turma;
import com.gestao.escolar.model.turma.TurmaDados;
import com.gestao.escolar.model.turma.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private AlunoRepository alunoRepo;
    @Autowired
    private TurmaRepository turmaRepo;
    
    @GetMapping
    public ModelAndView loadTurmaList(){
        ModelAndView mv = new ModelAndView();
        List<Turma> turmas = turmaRepo.findAll();
        mv.addObject("lista", turmas);
        mv.setViewName("turma/exibirTodos");
        return mv;
    }

    @GetMapping("/cadastrar")
    public ModelAndView loadTurmaForm(Long id){
        ModelAndView mv = new ModelAndView();
        List<Aluno> alunos = alunoRepo.getAlunosSemTurma();
        if(id != null ){
            Turma e = turmaRepo.getReferenceById(id);
            alunos.addAll(alunoRepo.getAlunosPorTurma(e));
            mv.addObject("turma", e);
        }
        mv.addObject("alunos", alunos);
        mv.setViewName("turma/cadastrar");
        return mv;
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ModelAndView criarTurma(TurmaDados dados){
        ModelAndView mv = new ModelAndView();
        List<Aluno> alunosMembros = alunoRepo.findAllById(dados.membros());
        Turma e = new Turma();
        e.setNome(dados.nome());
        e.setLocal(dados.local());
        e.setAlunos(alunosMembros);
        Turma turmaSaved = turmaRepo.save(e);
        for (Aluno aluno : alunosMembros) {
            aluno.setTurma(turmaSaved);
            alunoRepo.save(aluno);
        }

        mv.setViewName("redirect:/turma");
        return mv;
    }

    @DeleteMapping
    public String deletarTurma(Long id){
        Turma e = turmaRepo.getReferenceById(id);
        List<Aluno> alunosMembros = alunoRepo.getAlunosPorTurma(e);
        for (Aluno aluno : alunosMembros) {
            aluno.setTurma(null);
            alunoRepo.save(aluno);
        }
        turmaRepo.deleteById(id);
        return "redirect:turma";
    }

    
}
