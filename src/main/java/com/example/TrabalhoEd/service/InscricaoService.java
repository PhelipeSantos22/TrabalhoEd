package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Inscricao;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class InscricaoService {
    private static final String INSCRICOES_FILE = "src/main/resources/Inscricoes.csv";

    public void inserirInscricao(Inscricao inscricao) {
        try (FileWriter writer = new FileWriter(INSCRICOES_FILE, true)) {
            writer.write(inscricao.getCpfProfessor() + "," +
                    inscricao.getCodigoDisciplina() + "," +
                    inscricao.getCodigoDoProcesso() + "\n");
            System.out.println("Inscrição inserida: " + inscricao.getCpfProfessor());
        } catch (IOException e) {
            System.err.println("Erro ao inserir inscrição: " + e.getMessage());
        }
    }

    public Inscricao consultarInscricao(String cpfProfessor) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INSCRICOES_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals(cpfProfessor)) {
                    return new Inscricao(dados[0], dados[1], dados[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar inscrição: " + e.getMessage());
        }
        return null;
    }

    public void atualizarInscricao(Inscricao inscricao) {
        List<Inscricao> inscricoes = new ArrayList<>();
        boolean atualizado = false;

        // Ler todas as inscrições
        try (BufferedReader reader = new BufferedReader(new FileReader(INSCRICOES_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals(inscricao.getCpfProfessor())) {
                    inscricoes.add(inscricao); // Adiciona a inscrição atualizada
                    atualizado = true;
                } else {
                    inscricoes.add(new Inscricao(dados[0], dados[1], dados[2])); // Adiciona a inscrição antiga
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar inscrição: " + e.getMessage());
        }

        // Gravar as inscrições de volta no arquivo
        if (atualizado) {
            try (FileWriter writer = new FileWriter(INSCRICOES_FILE)) {
                for (Inscricao i : inscricoes) {
                    writer.write(i.getCpfProfessor() + "," +
                            i.getCodigoDisciplina() + "," +
                            i.getCodigoDoProcesso() + "\n");
                }
                System.out.println("Inscrição atualizada: " + inscricao.getCpfProfessor());
            } catch (IOException e) {
                System.err.println("Erro ao atualizar inscrições: " + e.getMessage());
            }
        }
    }

    public void removerInscricao(String cpfProfessor) {
        List<Inscricao> inscricoes = new ArrayList<>();

        // Ler todas as inscrições
        try (BufferedReader reader = new BufferedReader(new FileReader(INSCRICOES_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (!dados[0].equals(cpfProfessor)) {
                    inscricoes.add(new Inscricao(dados[0], dados[1], dados[2])); // Adiciona a inscrição não removida
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao remover inscrição: " + e.getMessage());
        }

        // Gravar as inscrições de volta no arquivo
        try (FileWriter writer = new FileWriter(INSCRICOES_FILE)) {
            for (Inscricao i : inscricoes) {
                writer.write(i.getCpfProfessor() + "," +
                        i.getCodigoDisciplina() + "," +
                        i.getCodigoDoProcesso() + "\n");
            }
            System.out.println("Inscrição removida: " + cpfProfessor);
        } catch (IOException e) {
            System.err.println("Erro ao remover inscrições: " + e.getMessage());
        }
    }
}
