package com.example.TrabalhoEd.controller;

import com.example.TrabalhoEd.model.Curso;
import com.example.TrabalhoEd.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    private final CursoService cursoService;

    @Autowired
    public CursosController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/inserir")
    public void inserirCurso(@RequestBody Curso curso) {
        cursoService.inserirCurso(curso);
    }

    @GetMapping("/consultar/{codigo}")
    public Curso consultarCurso(@PathVariable String codigo) {
        return cursoService.consultarCurso(codigo);
    }

    @PutMapping("/atualizar")
    public void atualizarCurso(@RequestBody Curso curso) {
        cursoService.atualizarCurso(curso);
    }

    @DeleteMapping("/remover/{codigo}")
    public void removerCurso(@PathVariable String codigo) {
        cursoService.removerCurso(codigo);
    }

}
