package com.gestao.escolar.model.turma;

import java.util.List;

public record TurmaDados(String nome, String local, List<Long> membros) {
    
}
