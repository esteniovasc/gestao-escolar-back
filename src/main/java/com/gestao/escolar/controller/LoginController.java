package com.gestao.escolar.controller;

import com.gestao.escolar.model.Aluno;
import com.gestao.escolar.model.usuario.UserDetailsImpl;
import com.gestao.escolar.model.usuario.Usuario;
import com.gestao.escolar.model.usuario.UsuarioRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/")
public class LoginController {
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public ModelAndView home(Authentication authentication) {
        ModelAndView mv = new ModelAndView("home");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        mv.addObject("userDetails", userDetails);
        mv.setViewName("home");
        return mv;
    }


    @GetMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
        return "redirect:/login";
    }
    
    
}
