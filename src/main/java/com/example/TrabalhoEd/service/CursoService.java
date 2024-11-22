package com.example.TrabalhoEd.service;

import br.edu.fateczl.fila.Fila;
import com.example.TrabalhoEd.model.Curso;
import com.example.TrabalhoEd.utils.ListaEncadeada;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CursoService {
    private final ListaEncadeada<Curso> listaCursos;
    private static final String CURSOS_FILE = "src/main/resources/Cursos.csv";

    public CursoService() {
        this.listaCursos = new ListaEncadeada<>(
                CURSOS_FILE,
                linha -> {
                    String[] campos = linha.split(",");
                    return new Curso(campos[0], campos[1], campos[2]);
                },
                curso -> curso.getCodigo() + "," + curso.getNome() + "," + curso.getAreaDeConhecimento()
        );
    }

    public void inserirCurso(Curso curso) {
        try (FileWriter writer = new FileWriter(CURSOS_FILE, true)) {
            writer.write(curso.getCodigo() + "," + curso.getNome() + "," + curso.getAreaDeConhecimento() + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao inserir curso: " + e.getMessage());
        }
    }
    // Consultar um curso usando fila
    public Curso consultarCurso(String codigo) {
        Fila<Curso> filaDeCursos = new Fila<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CURSOS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");

                filaDeCursos.insert(new Curso(campos[0], campos[1], campos[2]));
            }

            while (!filaDeCursos.isEmpty()) {
                Curso curso = filaDeCursos.remove();
                if (curso.getCodigo().equals(codigo)) {
                    return curso;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar curso: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao manipular a fila: " + e.getMessage());
        }
        return null;
    }
    // Atualiza o curso usando lista encadeada
    public void atualizarCurso(Curso cursoAtualizado) {
        listaCursos.atualizar(cursoAtualizado, c -> c.getCodigo().equals(cursoAtualizado.getCodigo()));// chama a função atualizar da lista e passa o criterio
    }
    // Remove um curso usando lista encadeada
    public void removerCurso(String codigo) {
        listaCursos.remover(c -> c.getCodigo().equals(codigo));// chama a função excluir da lista e passa o criterio
    }
}
