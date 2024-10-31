package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Disciplina;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class DisciplinaService {
    private static final String DISCIPLINAS_FILE = "src/main/resources/Disciplinas.csv";

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
        return null; // Retorna null se não encontrar
    }

    public void atualizarDisciplina(Disciplina disciplina) {
        List<Disciplina> disciplinas = new ArrayList<>();
        boolean atualizado = false;

        //ler todas as inscrições
        try (BufferedReader reader = new BufferedReader(new FileReader(DISCIPLINAS_FILE))){
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(",");
                if (dados[0].equals(disciplina.getCodigo())) {
                    disciplinas.add(disciplina);
                    atualizado = true;
                }else {
                    disciplinas.add(new Disciplina(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5] ));
                }
            }
        } catch (IOException e){
            System.err.println("Erro ao atualizar disciplina: " + e.getMessage());
        }
        if (atualizado){
            try (FileWriter writer = new FileWriter(DISCIPLINAS_FILE)){
                for (Disciplina d : disciplinas) {
                    writer.write(
                    d.getCodigo() + "," +
                        d.getNome() + "," +
                        d.getDiaDaSemana() + "," +
                        d.getHorarioInicial() + "," +
                        d.getHorasDiarias() + "," +
                        d.getCodigoCurso() + "\n"
                    );
                }
                System.out.println("Disciplinas atualizada: " + disciplina.getCodigo());
            } catch (IOException e) {
                System.err.println("Erro ao atualizar disciplina: " + e.getMessage());
            }
        }
    }

    public void removerDisciplina(String codigo) {
        List<Disciplina> disciplinas = listarDisciplinas();
        try (FileWriter writer = new FileWriter(DISCIPLINAS_FILE)) {
            for (Disciplina d : disciplinas) {
                if (!d.getCodigo().equals(codigo)) {
                    writer.write(d.getCodigo() + "," +
                            d.getNome() + "," +
                            d.getDiaDaSemana() + "," +
                            d.getHorarioInicial() + "," +
                            d.getHorasDiarias() + "," +
                            d.getCodigoCurso() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao remover disciplina: " + e.getMessage());
        }
    }

    private List<Disciplina> listarDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DISCIPLINAS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                disciplinas.add(new Disciplina(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]));
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }
        return disciplinas;
    }
}
