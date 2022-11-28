package br.edu.uniaeso.classes;

public class Disciplina {
    private int idDisciplina, idProfessor;
    private String cargaHoraria, nomeDisciplina;

    public Disciplina(){}

    public Disciplina(int id_disciplina, String carga_horaria, String nome_disciplina, int idProfessor) {
        this.idDisciplina = id_disciplina;
        this.cargaHoraria = carga_horaria;
        this.nomeDisciplina = nome_disciplina;
        this.idProfessor = idProfessor;
    }
      
    // Get e Set
    public String getCargaHoraria(){
        return cargaHoraria;
    }
    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    public String getNomeDisciplina(){
        return nomeDisciplina;
    }
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = cargaHoraria;
    }
    public int getIdDisciplina(){
        return idDisciplina;
    }
    public void setIdDisciplina(int idDisciplina){
        this.idDisciplina = idDisciplina;
    }
    public int getIdProfessor(){
        return idProfessor;
    }
    public void setIdProfessor(int idProfessor){
        this.idProfessor = idProfessor;
    }
}