package com.example.TrabalhoEd.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvService {
    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String DISCIPLINAS_FILE = RESOURCES_PATH + "Disciplinas.csv";
    private static final String CURSOS_FILE = RESOURCES_PATH + "Cursos.csv";
    private static final String PROFESSORES_FILE = RESOURCES_PATH + "Professores.csv";
    private static final String INSCRICOES_FILE = RESOURCES_PATH + "Inscricoes.csv";

    public static void verificarOuCriarArquivos() {
        criarArquivoComCabecalho(DISCIPLINAS_FILE, "Código,Nome,Dia da Semana,Horário Inicial,Horas Diárias,Código do Curso");
        criarArquivoComCabecalho(CURSOS_FILE, "Código,Nome,Área de Conhecimento");
        criarArquivoComCabecalho(PROFESSORES_FILE, "CPF,Nome,Área de Conhecimento,Pontos");
        criarArquivoComCabecalho(INSCRICOES_FILE, "CPF do Professor,Código da Disciplina,Código do Processo");
    }

    private static void criarArquivoComCabecalho(String nomeArquivo, String cabecalho) {
        Path caminhoArquivo = Paths.get(nomeArquivo);
        if (Files.notExists(caminhoArquivo)) {
            try (FileWriter writer = new FileWriter(nomeArquivo)) {
                writer.write(cabecalho + "\n");
                System.out.println("Arquivo criado com cabeçalho: " + nomeArquivo);
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo " + nomeArquivo + ": " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo já existe: " + nomeArquivo);
        }
    }
}
