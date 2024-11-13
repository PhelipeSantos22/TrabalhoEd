package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Curso;
import com.example.TrabalhoEd.model.ListaEncadeada;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CursoService {
    private final ListaEncadeada<Curso> listaCursos;
    private static final String CURSOS_FILE = "src/main/resources/Cursos.csv"; // caminho para o arquivo csv

    public CursoService() {
        this.listaCursos = new ListaEncadeada<>(
                CURSOS_FILE,
                linha -> {
                    String[] campos = linha.split(",");
                    return new Curso(campos[0], campos[1], campos[2]);
                },
                curso -> curso.getCodigo() + "," + curso.getNome() + "," + curso.getAreaDeConhecimento()
        );
    }

    public void inserirCurso(Curso curso) {
        try (FileWriter writer = new FileWriter(CURSOS_FILE, true)) {
            writer.write(curso.getCodigo() + "," + curso.getNome() + "," + curso.getAreaDeConhecimento() + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao inserir curso: " + e.getMessage());
        }
    }

    public Curso consultarCurso(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CURSOS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos[0].equals(codigo)) {
                    return new Curso(campos[0], campos[1], campos[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar curso: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar o curso
    }

    public void atualizarCurso(Curso cursoAtualizado) {
        listaCursos.atualizar(cursoAtualizado, c -> c.getCodigo().equals(cursoAtualizado.getCodigo()));// chama a função atualizar da lista e passa o criterio
    }

    public void removerCurso(String codigo) {
        listaCursos.remover(c -> c.getCodigo().equals(codigo));// chama a função excluir da lista e passa o criterio
    }
}
