package com.example.TrabalhoEd.utils;

import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class ListaEncadeada<T> {
    private final String caminhoArquivo;
    private final Function<String, T> parseLinha; // Função para converter uma linha de texto em um objeto do tipo T.
    private final Function<T, String> toLinha; // Função para converter um objeto do tipo T em uma linha de texto.

    public ListaEncadeada(String caminhoArquivo, Function<String, T> parseLinha, Function<T, String> toLinha) {
        this.caminhoArquivo = caminhoArquivo;
        this.parseLinha = parseLinha;
        this.toLinha = toLinha;
    }

    public List<T> carregar() {
        List<T> lista = new LinkedList<>();
        Path caminho = Paths.get(caminhoArquivo);
        try {
            List<String> linhas = Files.readAllLines(caminho);
            for (String linha : linhas) {
                lista.add(parseLinha.apply(linha));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
        return lista;
    }

    public void salvar(List<T> lista) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            for (T item : lista) {
                writer.write(toLinha.apply(item) + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    public void atualizar(T itemAtualizado, Function<T, Boolean> criterio) {
        List<T> lista = carregar();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (criterio.apply(lista.get(i))) {
                lista.set(i, itemAtualizado);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            salvar(lista);
        } else {
            System.out.println("Item não encontrado para atualização.");
        }
    }

    public void remover(Function<T, Boolean> criterio) {
        List<T> lista = carregar();
        lista.removeIf(criterio::apply);
        salvar(lista);
    }
}
