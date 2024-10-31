package com.example.TrabalhoEd.controller;

import com.example.TrabalhoEd.model.Inscricao;
import com.example.TrabalhoEd.service.InscricaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @PostMapping("/inserir")
    public void inserirInscricao(@RequestBody Inscricao inscricao) {
        inscricaoService.inserirInscricao(inscricao);
    }

    @GetMapping("/consultar/{cpfProfessor}")
    public Inscricao consultarInscricao(@PathVariable String cpfProfessor) {
        return inscricaoService.consultarInscricao(cpfProfessor);
    }

    @PutMapping("/atualizar")
    public void atualizarInscricao(@RequestBody Inscricao inscricao) {
        inscricaoService.atualizarInscricao(inscricao);
    }

    @DeleteMapping("/remover/{cpfProfessor}")
    public void removerInscricao(@PathVariable String cpfProfessor) {
        inscricaoService.removerInscricao(cpfProfessor);
    }
}
