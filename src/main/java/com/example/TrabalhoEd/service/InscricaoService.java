package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Inscricao;
import com.example.TrabalhoEd.model.ListaEncadeada;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InscricaoService {
    private final ListaEncadeada<Inscricao> listaInscricao;
    private static final String INSCRICOES_FILE = "src/main/resources/Inscricoes.csv";
    private static final String PROFESSORES_FILE = "src/main/resources/Professores.csv";

    public InscricaoService() {
        this.listaInscricao = new ListaEncadeada<>(
                INSCRICOES_FILE,
                linha -> {
                    String[] campos = linha.split(",");
                    return new Inscricao(campos[0], campos[1], campos[2]); // Converte linha em objeto Inscricao
                },
                inscricao -> inscricao.getCpfProfessor() + "," + inscricao.getCodigoDisciplina() + "," + inscricao.getCodigoDoProcesso() // Converte objeto Inscricao em linha
        );
    }

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
        List<Inscricao> inscricoes = listaInscricao.carregar(); // Carrega todas as inscrições do arquivo
        for (Inscricao inscricao : inscricoes) {
            if (inscricao.getCpfProfessor().equals(cpfProfessor)) {
                return inscricao; // Retorna a inscrição correspondente
            }
        }
        return null;
    }

    // Está função foi feita com o auxilio do ChatGpt para trabalhar com MAP
    public List<Map<String, String>> consultarInscricoesDisciplina(String codigoDisciplina) {
        List<Map<String, String>> resultado = new ArrayList<>();

        try (BufferedReader inscricoesReader = new BufferedReader(new FileReader(INSCRICOES_FILE));
             BufferedReader professoresReader = new BufferedReader(new FileReader(PROFESSORES_FILE))) {

            // Criar um mapa para armazenar os dados dos professores.
            Map<String, String[]> professores = new HashMap<>();
            String linhaProfessor;

            while ((linhaProfessor = professoresReader.readLine()) != null) {
                String[] dados = linhaProfessor.split(",");
                professores.put(dados[0], new String[]{dados[1], dados[2], dados[3]}); // Nome, Área, Pontos
            }

            // Filtrar inscrições com o código da disciplina e incluir dados do professor
            String linhaInscricao;
            while ((linhaInscricao = inscricoesReader.readLine()) != null) {
                String[] dadosInscricao = linhaInscricao.split(",");

                if (dadosInscricao[1].equals(codigoDisciplina)) {
                    Map<String, String> detalhes = new HashMap<>();
                    detalhes.put("codigoDisciplina", dadosInscricao[1]);
                    detalhes.put("codigoDoProcesso", dadosInscricao[2]);
                    detalhes.put("cpfProfessor", dadosInscricao[0]);

                    // Adicionar informações do professor
                    String[] dadosProfessor = professores.get(dadosInscricao[0]);
                    if (dadosProfessor != null) {
                        detalhes.put("nomeProfessor", dadosProfessor[0]);
                        detalhes.put("areaConhecimento", dadosProfessor[1]);
                        detalhes.put("pontos", dadosProfessor[2]);
                    } else {
                        detalhes.put("nomeProfessor", "Desconhecido");
                        detalhes.put("areaConhecimento", "Desconhecido");
                        detalhes.put("pontos", "Desconhecido");
                    }

                    resultado.add(detalhes);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar inscrições: " + e.getMessage());
        }
        return resultado;
    }

    public void atualizarInscricao(Inscricao inscricaoAtualizada) {
        listaInscricao.atualizar(inscricaoAtualizada, i -> i.getCodigoDoProcesso().equals(inscricaoAtualizada.getCodigoDoProcesso())); // Atualiza com base no código do processo
    }

    public void removerInscricao(String cpfProfessor) {
        listaInscricao.remover(i -> i.getCpfProfessor().equals(cpfProfessor)); // Remove com base no CPF do professor
    }
}
