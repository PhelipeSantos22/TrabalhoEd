package com.example.TrabalhoEd.utils;

import java.util.List;
import java.util.Map;

public class Ordenacao {
    // bubble sort para ordenar os professores por ponto
    public static void ordenarPorPontos(List<Map<String, String>> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                int pontosAtual = Integer.parseInt(lista.get(j).get("pontos"));
                int pontosProximo = Integer.parseInt(lista.get(j + 1).get("pontos"));

                if (pontosAtual < pontosProximo) {
                    Map<String, String> temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }
}
