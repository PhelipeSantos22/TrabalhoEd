package com.example.TrabalhoEd.controller;

import com.example.TrabalhoEd.model.Disciplina;
import com.example.TrabalhoEd.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping("/inserir")
    public void inserirDisciplina(@RequestBody Disciplina disciplina){
        disciplinaService.inserirDisciplina(disciplina);
    }

    @GetMapping("/consultar/{codigo}")
    public Disciplina consultarDisciplina(@PathVariable String codigo) {
        return disciplinaService.consultarDisciplina(codigo);
    }

    @PutMapping("/atualizar")
    public void atualizarDisciplina(@RequestBody Disciplina disciplina) {
        disciplinaService.atualizarDisciplina(disciplina);
    }

    @DeleteMapping("/remover/{codigo}")
    public void removerDisciplina(@PathVariable String codigo) {
        disciplinaService.removerDisciplina(codigo);
    }
}
