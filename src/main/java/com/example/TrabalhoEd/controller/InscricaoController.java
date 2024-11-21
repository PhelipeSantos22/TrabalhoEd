package com.example.TrabalhoEd.controller;

import com.example.TrabalhoEd.model.Inscricao;
import com.example.TrabalhoEd.service.InscricaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/consultar/disciplinas/{codigoDisciplina}")
    public ResponseEntity<List<Map<String, String>>> consultarInscricoesPorDisciplina(
            @PathVariable String codigoDisciplina) {
        List<Map<String, String>> resultado = inscricaoService.consultarInscricoesDisciplina(codigoDisciplina);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resultado);
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
