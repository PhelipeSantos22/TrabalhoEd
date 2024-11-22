package com.example.TrabalhoEd.model;

public class Professor {
    private String cpf;
    private String nome;
    private String areaDeConhecimento;
    private String pontos;

    public Professor(String cpf, String nome, String areaDeConhecimento, String pontos) {
        this.cpf = cpf;
        this.nome = nome;
        this.areaDeConhecimento = areaDeConhecimento;
        this.pontos = pontos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
    public String getPontos() {
        return pontos;
    }
    public void setPontos(String pontos) {
        this.pontos = pontos;
    }
}
