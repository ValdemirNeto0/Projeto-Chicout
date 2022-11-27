package br.edu.uniaeso.classes;

public class Aluno {
    private int idAluno,idTurma,idCurso,idade;
    private String nome,endereco,email;

    public Aluno(){}

    public Aluno(int idAluno, int idade, String nome, String endereco, String email, int idCurso, int idTurma) {
        this.idAluno = idAluno;
        this.idade = idade;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.idCurso = idCurso;
        this.idTurma = idTurma;
    }

    //get e setters
    public int getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
