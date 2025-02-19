package com.gestao.escolar.controller;

import com.gestao.escolar.model.usuario.Usuario;
import com.gestao.escolar.model.usuario.UsuarioRepository;
import com.gestao.escolar.model.usuario.UsuarioRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("cadastro")
    public String loadNovoUsuarioForm(){
        return "usuario/cadastro";
    }


    @Transactional
    @PostMapping("salvar")
    public String guardarUsuario(UsuarioRequestDTO dados){
        System.out.println("Salvando " + dados);
        String password = passwordEncoder.encode(dados.password());
        Usuario u = new Usuario(dados.username(), dados.email(), password);
        usuarioRepo.save(u);
        return "redirect:/login";
    }

}