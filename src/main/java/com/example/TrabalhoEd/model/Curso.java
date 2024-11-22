package com.example.TrabalhoEd.model;

public class Curso {
    private String codigo;
    private String nome;
    private String areaDeConhecimento;

    public Curso(String codigo, String nome, String areaDeConhecimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.areaDeConhecimento = areaDeConhecimento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaDeConhecimento() {
        return areaDeConhecimento;
    }

    public void setAreaDeConhecimento(String areaDeConhecimento) {
        this.areaDeConhecimento = areaDeConhecimento;
    }
}
