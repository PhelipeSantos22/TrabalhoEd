package com.example.TrabalhoEd.model;

public class Disciplina {
    private String codigo;
    private String nome;
    private String diaDaSemana;
    private String horarioInicial;
    private String horasDiarias;
    private String codigoCurso;

    public Disciplina(String codigo, String nome, String diaDaSemana, String horarioInicial, String horasDiarias, String codigoCurso) {
        this.codigo = codigo;
        this.nome = nome;
        this.diaDaSemana = diaDaSemana;
        this.horarioInicial = horarioInicial;
        this.horasDiarias = horasDiarias;
        this.codigoCurso = codigoCurso;
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

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getHorasDiarias() {
        return horasDiarias;
    }

    public void setHorasDiarias(String horasDiarias) {
        this.horasDiarias = horasDiarias;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
}
