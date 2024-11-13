package com.example.TrabalhoEd.service;

import com.example.TrabalhoEd.model.Inscricao;
import com.example.TrabalhoEd.model.ListaEncadeada;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class InscricaoService {
    private final ListaEncadeada<Inscricao> listaInscricao;
    private static final String INSCRICOES_FILE = "src/main/resources/Inscricoes.csv";

    public InscricaoService() {
        this.listaInscricao = new ListaEncadeada<>(
                INSCRICOES_FILE,
                linha -> {
                    String[] campos = linha.split(",");
                    return new Inscricao(campos[0], campos[1], campos[2]);
                },
                inscricao -> inscricao.getCpfProfessor() + "," + inscricao.getCodigoDisciplina() + "," + inscricao.getCodigoDoProcesso()
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
    public void atualizarInscricao(Inscricao InscricaoAtualizada) {
        listaInscricao.atualizar(InscricaoAtualizada, i -> i.getCodigoDoProcesso().equals(InscricaoAtualizada.getCodigoDoProcesso()));// chama a função atualizar da lista e passa o criterio
    }

    public void removerInscricao(String cpfProfessor) {
        listaInscricao.remover(i -> i.getCpfProfessor().equals(cpfProfessor));// chama a função excluir da lista e passa o criterio
    }

}
