package com.example.TrabalhoEd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/professores")
    public String gerenciarProfessores() {
        return "professores";
    }
    @GetMapping("/disciplinas")
    public String gerenciarDisciplinas() {
        return "disciplinas";
    }
    @GetMapping("/cursos")
    public String gerenciarCursos() {
        return "cursos";
    }
    @GetMapping("/inscricoes")
    public String gerenciarInscricoes() {
        return "inscricoes";
    }
}
