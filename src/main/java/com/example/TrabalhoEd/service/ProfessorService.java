package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.ListaEncadeada;
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
    private final ListaEncadeada<Professor> listaProfessor;
    private static final String PROFESSORES_FILE = "src/main/resources/Professores.csv";

    public ProfessorService() {
        this.listaProfessor = new ListaEncadeada<>(
                PROFESSORES_FILE,
                linha -> {
                    String[] campos = linha.split(",");
                    return new Professor(campos[0], campos[1], campos[2], campos[3] );
                },
                professor -> professor.getCpf() + "," + professor.getNome() + "," + professor.getAreaDeConhecimento() + "," + professor.getPontos()
        );
    }

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

    public void atualizarProfessor(Professor professorAtualizado){
        listaProfessor.atualizar(professorAtualizado, p -> p.getCpf().equals(professorAtualizado.getCpf()));// chama a função atualizar da lista e passa o criterio
    }

    public void removerProfessor(String cpf) {
        listaProfessor.remover(p -> p.getCpf().equals(cpf));// chama a função excluir da lista e passa o criterio
    }
}

