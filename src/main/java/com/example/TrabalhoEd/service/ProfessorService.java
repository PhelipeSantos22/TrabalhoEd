package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Professor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProfessorService {
    private static final String PROFESSORES_FILE = "src/main/resources/Professores.csv";

    public void inserirProfessor(Professor professor) {
        try (FileWriter writer = new FileWriter(PROFESSORES_FILE, true)) {
            writer.write(
                professor.getCpf() + "," +
                    professor.getNome() + "," +
                    professor.getAreaDeConhecimento() + "," +
                    professor.getPontos() + "\n"
                    );
        } catch (IOException e) {
            System.err.println("Erro ao inserir professor: " + e.getMessage());
        }
    }

    public Professor consultarProfessor(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFESSORES_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos[0].equals(cpf)) {
                    return new Professor(campos[0], campos[1], campos[2], campos[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar professor: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar o professor
    }

    public void atualizarProfessor(Professor professor) {
        Path caminhoArquivo = Paths.get(PROFESSORES_FILE);
        try {
            var linhas = Files.readAllLines(caminhoArquivo);
            try (FileWriter writer = new FileWriter(PROFESSORES_FILE)) {
                for (String linha : linhas) {
                    String[] campos = linha.split(",");
                    if (campos[0].equals(professor.getCpf())) {
                        writer.write(
                                professor.getCpf() + "," +
                                    professor.getNome() + "," +
                                    professor.getAreaDeConhecimento() + "," +
                                    professor.getPontos() + "\n"
                                );
                    } else {
                        writer.write(linha + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    public void removerProfessor(String cpf) {
        Path caminhoArquivo = Paths.get(PROFESSORES_FILE);
        try {
            var linhas = Files.readAllLines(caminhoArquivo);
            try (FileWriter writer = new FileWriter(PROFESSORES_FILE)) {
                for (String linha : linhas) {
                    String[] campos = linha.split(",");
                    if (!campos[0].equals(cpf)) {
                        writer.write(linha + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao remover professor: " + e.getMessage());
        }
    }
}

