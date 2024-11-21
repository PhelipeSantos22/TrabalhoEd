package com.example.TrabalhoEd.utils;

import com.example.TrabalhoEd.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class HashTable {

    private List<List<Disciplina>> hashTable;
    private int tableSize;

    // Construtor
    public HashTable(int tableSize) {
        this.tableSize = tableSize;
        initializeHashTable();
    }

    // Inicializa
    private void initializeHashTable() {
        hashTable = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            hashTable.add(new ArrayList<>());
        }
    }

    // Função hash que pega os caractres da string (codigoCurso) para calcular o índice da tabela
    public int hash(String codigoCurso) {
        int hash = 0;
        for (char c : codigoCurso.toCharArray()) {
            hash += c;
        }
        return hash % tableSize; // garante que o index esta dentro do tamanho da tabela
    }

    // Adiciona uma disciplina à tabela de hash
    public void addDisciplina(Disciplina disciplina) {
        int Index = hash(disciplina.getCodigoCurso());
        hashTable.get(Index).add(disciplina);
    }

    // Consulta disciplinas por código do curso
    public List<Disciplina> consultarDisciplinasPorCurso(String codigoCurso) {
        int Index = hash(codigoCurso);
        List<Disciplina> disciplinas = hashTable.get(Index);

        // Filtra pelo codigo do curso
        return disciplinas.stream()
                .filter(d -> d.getCodigoCurso().equals(codigoCurso))
                .toList();
    }
}
