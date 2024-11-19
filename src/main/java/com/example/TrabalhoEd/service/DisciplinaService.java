package com.example.TrabalhoEd.service;

import br.edu.fateczl.fila.Fila;
import com.example.TrabalhoEd.model.Disciplina;
import com.example.TrabalhoEd.model.Inscricao;
import com.example.TrabalhoEd.model.ListaEncadeada;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class DisciplinaService {
    private final ListaEncadeada<Inscricao> listaInscricao;
    private static final String INSCRICOES_FILE = "src/main/resources/Inscricoes.csv";

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
        this.listaInscricao = new ListaEncadeada<>(
                INSCRICOES_FILE,
                linha -> {
                    String[] campos = linha.split(",");
                    return new Inscricao(campos[0], campos[1], campos[2]);
                },
                inscricao -> inscricao.getCpfProfessor() + "," + inscricao.getCodigoDisciplina() + "," + inscricao.getCodigoDoProcesso()
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
    // Consultar uma disciplina usando fila
    public Disciplina consultarDisciplina(String codigo) {
        Fila<Disciplina> filaDeDisciplinas = new Fila<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DISCIPLINAS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                filaDeDisciplinas.insert(new Disciplina(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]));
            }

            while(!filaDeDisciplinas.isEmpty()){
                Disciplina disciplina = filaDeDisciplinas.remove();
                if (disciplina.getCodigo().equals(codigo)){
                    return disciplina;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar disciplina: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao manipular a fila: " + e.getMessage());
        }
        return null;
    }

    public void atualizarDisciplina(Disciplina disciplinaAtualizada) {
        listaDisciplina.atualizar(disciplinaAtualizada, d -> d.getCodigo().equals(disciplinaAtualizada.getCodigo()));// chama a função atualizar da lista e passa o criterio
    }

    public void removerDisciplina(String codigo) {
       listaDisciplina.remover(d -> d.getCodigo().equals(codigo) ); // chama a função atualizar da lista e passa o criterio
       listaInscricao.remover(i -> i.getCodigoDisciplina().equals(codigo)); // quando deletar uma disciplina remove todas as inscrições que tem o codigo da disciplina
    }
}
