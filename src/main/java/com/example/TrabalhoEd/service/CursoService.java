package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Curso;
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
    private static final String CURSOS_FILE = "src/main/resources/Cursos.csv";

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

    public void atualizarCurso(Curso curso) {
        Path caminhoArquivo = Paths.get(CURSOS_FILE);
        try {
            var linhas = Files.readAllLines(caminhoArquivo);
            try (FileWriter writer = new FileWriter(CURSOS_FILE)) {
                for (String linha : linhas) {
                    String[] campos = linha.split(",");
                    if (campos[0].equals(curso.getCodigo())) {
                        writer.write(curso.getCodigo() + "," + curso.getNome() + "," + curso.getAreaDeConhecimento() + "\n");
                    } else {
                        writer.write(linha + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    public void removerCurso(String codigo) {
        Path caminhoArquivo = Paths.get(CURSOS_FILE);
        try {
            var linhas = Files.readAllLines(caminhoArquivo);
            try (FileWriter writer = new FileWriter(CURSOS_FILE)) {
                for (String linha : linhas) {
                    String[] campos = linha.split(",");
                    if (!campos[0].equals(codigo)) {
                        writer.write(linha + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao remover curso: " + e.getMessage());
        }
    }
}
