package br.edu.uniaeso.classes;
public class Professor {
    private String NomeProfessor, EmailProf, EnderecoProf,TelefoneProf;
    private int IdadeProf, idProfessor, idTurma, idCurso;

    public Professor(){}

    public Professor(String nomeProfessor, String emailProf, String enderecoProf, int idProfessor, int idadeProf, String telefoneProf, int idTurma, int idCurso) {
        this.NomeProfessor = nomeProfessor;
        this.EmailProf = emailProf;
        this.EnderecoProf = enderecoProf;
        this.idProfessor = idProfessor;
        this.IdadeProf = idadeProf;
        this.TelefoneProf = telefoneProf;
        this.idCurso = idCurso;
        this.idTurma = idTurma;
    }

    public String getNomeProfessor() {
        return NomeProfessor;
    }
    public void setNomeProfessor(String nomeProfessor) {
        NomeProfessor = nomeProfessor;
    }
    public String getEmailProf() {
        return EmailProf;
    }
    public void setEmailProf(String emailProf) {
        EmailProf = emailProf;
    }
    public String getEnderecoProf() {
        return EnderecoProf;
    }
    public void setEnderecoProf(String enderecoprof) {
        EnderecoProf = enderecoprof;
    }
    public int getIdProfessor() {
        return idProfessor;
    }
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
    public int getIdadeProf() {
        return IdadeProf;
    }
    public void setIdadeProf(int idadeProf) {
        IdadeProf = idadeProf;
    }
    public String getTelefoneProf() {
        return TelefoneProf;
    }
    public void setTelefoneProf(String telefoneprof) {
        TelefoneProf = telefoneprof;
    }
    public int getIdTurma() {
        return idTurma;
    }
    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
    public int getIdCurso() {
        return idCurso;
    }
    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
