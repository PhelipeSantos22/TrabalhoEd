package com.example.TrabalhoEd.controller;

import com.example.TrabalhoEd.model.Professor;
import com.example.TrabalhoEd.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/inserir")
    public void inserirCurso(@RequestBody Professor professor) {
        professorService.inserirProfessor(professor);
    }

    @GetMapping("/consultar/{cpf}")
    public Professor consultarProfessor(@PathVariable String cpf) {
        return professorService.consultarProfessor(cpf);
    }

    @PutMapping("/atualizar")
    public void atualizarProfessor(@RequestBody Professor professor) {
        professorService.atualizarProfessor(professor);
    }

    @DeleteMapping("/remover/{cpf}")
    public void removerProfessor(@PathVariable String cpf) {
        professorService.removerProfessor(cpf);
    }
}
