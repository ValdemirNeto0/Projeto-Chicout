package br.edu.uniaeso.classes;

public class Curso {
    private int idCurso, cargaHoraria,qtdAlunos, qtdProfessores;
    private String nomeCurso;

    public Curso(){}

    public Curso(int idCurso, int cargaHoraria, int qtdAlunos, int qtdProfessores, String nomeCurso){
        this.idCurso = idCurso;
        this.cargaHoraria = cargaHoraria;
        this.qtdAlunos = qtdAlunos;
        this.qtdProfessores = qtdProfessores;
        this.nomeCurso = nomeCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }
    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    public int getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    public int getQtdAlunos() {
        return qtdAlunos;
    }
    public void setQtdAlunos(int qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }
    public int getQtdProfessores() {
        return qtdProfessores;
    }
    public void setQtdProfessores(int qtdProfessores) {
        this.qtdProfessores = qtdProfessores;
    }
    public String getNomeCurso() {
        return nomeCurso;
    }
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
