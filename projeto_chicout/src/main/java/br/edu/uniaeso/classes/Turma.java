package br.edu.uniaeso.classes;
public class Turma {
    private int idTurma, qtdAlunos, idAluno, idDisciplina, idCurso;
    private String turmaNome;

    public Turma(){}

    public Turma(String turmaNome, int qtdAlunos, int idTurma, int idAluno, int idDisciplina, int idCurso) {
        setTurmaNome(turmaNome);
        setIdTurma(idTurma);
        setQtdAlunos(qtdAlunos);
        setIdAluno(idAluno);
        setIdDisciplina(idDisciplina);
        setIdCurso(idCurso);
    }
    
    public int getQtdAlunos() {
        return qtdAlunos;
    }
    public String getTurmaNome() {
        return turmaNome;
    }
    public void setQtdAlunos(int qTdAlunos) {
        this.qtdAlunos = qTdAlunos;
    }
    public void setTurmaNome(String turmaNome) {
        this.turmaNome = turmaNome;
    }
    public int getIdTurma() {
        return idTurma;
    }
    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
    public int getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
    public int getIdDisciplina() {
        return idDisciplina;
    }
    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
    public int getIdCurso() {
        return idCurso;
    }
    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
