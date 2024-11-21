package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Inscricao;
import com.example.TrabalhoEd.model.ListaEncadeada;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class InscricaoService {
    private final ListaEncadeada<Inscricao> listaInscricao;
    private static final String INSCRICOES_FILE = "src/main/resources/Inscricoes.csv";

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
        return null; // Retorna null se não encontrar
    }

    public List<Inscricao> consultarInscricoesDisciplina(String codigoDisciplina) {
        List<Inscricao> inscricoes = listaInscricao.carregar();
        return inscricoes.stream()
                .filter(inscricao -> inscricao.getCodigoDisciplina().equals(codigoDisciplina)) // Filtra pelo código da disciplina
                .toList();
    }

    public void atualizarInscricao(Inscricao inscricaoAtualizada) {
        listaInscricao.atualizar(inscricaoAtualizada, i -> i.getCodigoDoProcesso().equals(inscricaoAtualizada.getCodigoDoProcesso())); // Atualiza com base no código do processo
    }

    public void removerInscricao(String cpfProfessor) {
        listaInscricao.remover(i -> i.getCpfProfessor().equals(cpfProfessor)); // Remove com base no CPF do professor
    }
}
