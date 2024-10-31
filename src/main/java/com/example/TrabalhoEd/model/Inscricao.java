package com.example.TrabalhoEd.model;

public class Inscricao {
    private String cpfProfessor;
    private String codigoDisciplina;
    private String codigoDoProcesso;

    public Inscricao(String cpfProfessor, String codigoDisciplina, String codigoDoProcesso) {
        this.cpfProfessor = cpfProfessor;
        this.codigoDisciplina = codigoDisciplina;
        this.codigoDoProcesso = codigoDoProcesso;
    }

    public String getCpfProfessor() {
        return cpfProfessor;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public String getCodigoDoProcesso() {
        return codigoDoProcesso;
    }
}
