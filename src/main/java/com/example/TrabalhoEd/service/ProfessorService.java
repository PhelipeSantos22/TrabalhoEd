package com.example.TrabalhoEd.service;

import br.edu.fateczl.fila.Fila;
import com.example.TrabalhoEd.utils.ListaEncadeada;
import com.example.TrabalhoEd.model.Professor;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

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

    // Consultar um professor usando fila
    public Professor consultarProfessor(String cpf) {
        Fila<Professor> filaDeProfessores = new Fila<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFESSORES_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");
                filaDeProfessores.insert( new Professor(campos[0], campos[1], campos[2], campos[3]));
            }

            while(!filaDeProfessores.isEmpty()){
                Professor professor = filaDeProfessores.remove();
                if (professor.getCpf().equals(cpf)){
                    return professor;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar professor: " + e.getMessage());
        }  catch (Exception e) {
            System.err.println("Erro ao manipular a fila: " + e.getMessage());
        }
        return null;
    }
    // Atualiza usando lista encadeada
    public void atualizarProfessor(Professor professorAtualizado){
        listaProfessor.atualizar(professorAtualizado, p -> p.getCpf().equals(professorAtualizado.getCpf()));
    }
    // Remover usando lista encadeada
    public void removerProfessor(String cpf) {
        listaProfessor.remover(p -> p.getCpf().equals(cpf));
    }
}

