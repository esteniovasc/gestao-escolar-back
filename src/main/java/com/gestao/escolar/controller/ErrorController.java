package com.gestao.escolar.controller;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping
    public String handleError(Model model) {
        model.addAttribute("message", "A página não foi encontrada!");
        return "error"; // A página HTML que você vai criar para o erro
    }

    // Para erro 403 (Acesso negado)
    @GetMapping("/error/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handle403Error(Model model) {
        model.addAttribute("message", "Você não tem permissão para acessar esta página.");
        return "error403";
    }


}
