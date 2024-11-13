package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Disciplina;
import com.example.TrabalhoEd.model.ListaEncadeada;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class DisciplinaService {
    private final ListaEncadeada<Disciplina> listaDisciplina;
    private static final String DISCIPLINAS_FILE = "src/main/resources/Disciplinas.csv";

    public DisciplinaService() {
        this.listaDisciplina = new ListaEncadeada<>(
                DISCIPLINAS_FILE,
                linha -> {
                    String[] campos = linha.split(",");
                    return new Disciplina(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]);
                },
                disciplina -> disciplina.getCodigo() + "," + disciplina.getNome() + "," + disciplina.getDiaDaSemana() + "," + disciplina.getHorarioInicial() + "," + disciplina.getHorasDiarias() + "," + disciplina.getCodigoCurso()
        );
    }

    public void inserirDisciplina(Disciplina disciplina) {
        try (FileWriter writer = new FileWriter(DISCIPLINAS_FILE, true)) {
            writer.write(
                    disciplina.getCodigo() + "," +
                    disciplina.getNome() + "," +
                    disciplina.getDiaDaSemana() + "," +
                    disciplina.getHorarioInicial() + "," +
                    disciplina.getHorasDiarias() + "," +
                    disciplina.getCodigoCurso() + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao inserir disciplina: " + e.getMessage());
        }
    }

    public Disciplina consultarDisciplina(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DISCIPLINAS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals(codigo)) {
                    return new Disciplina(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar disciplina: " + e.getMessage());
        }
        return null;
    }

    public void atualizarDisciplina(Disciplina disciplinaAtualizada) {
        listaDisciplina.atualizar(disciplinaAtualizada, d -> d.getCodigo().equals(disciplinaAtualizada.getCodigo()));// chama a função atualizar da lista e passa o criterio
    }

    public void removerDisciplina(String codigo) {
       listaDisciplina.remover(d -> d.getCodigo().equals(codigo) ); // chama a função atualizar da lista e passa o criterio
    }
}
