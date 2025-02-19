package com.gestao.escolar.controller;

import com.gestao.escolar.model.Aluno;
import com.gestao.escolar.model.AlunoRepository;
import com.gestao.escolar.model.turma.Turma;
import com.gestao.escolar.model.turma.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("aluno")
public class AlunoController {
    
    @Autowired
    private AlunoRepository alunoRepo;
    @Autowired
    private TurmaRepository turmaRepo;
    
    @GetMapping("cadastrar")
    public ModelAndView cadastrarAluno(Long id){
        ModelAndView mv = new ModelAndView();
        if(id!= null){
            Aluno a = alunoRepo.getReferenceById(id);
            mv.addObject("aluno", a);
        }
        List<Turma> turmas = turmaRepo.findAll();
        mv.addObject("turmas", turmas);
        mv.setViewName("aluno/cadastrar");
        return mv;
    }

    @PostMapping("cadastrar")
    public String salvar(String nome, Integer idade, Boolean necessidadeEspecial, Boolean possuiLaudo, Integer codigoCid, Long turma){
        Aluno a = new Aluno(nome, idade, necessidadeEspecial, possuiLaudo, codigoCid);
        if(turma != 0){
            Turma e = turmaRepo.getReferenceById(turma);
            a.setTurma(e);
        }
        alunoRepo.save(a);
        return "redirect:/aluno";
    }

    @GetMapping("consultar")
    public ModelAndView consultar(Long id){
        ModelAndView mv = new ModelAndView();
        Aluno a = alunoRepo.getReferenceById(id);
        mv.addObject("aluno", a);
        mv.setViewName("aluno/exibir");
        return mv;
    }
    @GetMapping
    public ModelAndView obterTodos(){
        ModelAndView mv =new ModelAndView();
        List<Aluno> lista = alunoRepo.findAll();
        mv.addObject("lista", lista);
        mv.setViewName("aluno/exibirTodos");
        return mv;
    }

    @DeleteMapping
    public String deletarAluno(Long id){
        alunoRepo.deleteById(id);
        return "redirect:aluno";
    }

    @PutMapping("cadastrar")
    @Transactional
    public String atualizarAluno(Long id, String nome, Integer idade, Boolean necessidadeEspecial, Boolean possuiLaudo, Integer codigoCid, Long turma){
        Aluno a = alunoRepo.getReferenceById(id);
        a.setNome(nome);
        a.setIdade(idade);
        a.setNecessidadeEspecial(necessidadeEspecial);
        a.setPossuiLaudo(possuiLaudo);
        a.setCodigoCid(codigoCid);
        if(turma != 0){
            Turma e = turmaRepo.getReferenceById(turma);
            a.setTurma(e);
        }
        return "redirect:/aluno";
    }

}
