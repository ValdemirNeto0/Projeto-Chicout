package br.edu.uniaeso;
import br.edu.uniaeso.classes.*;

public class Teste 
{
    public static void main( String[] args )
    {
        Aluno aluno = new Aluno(1, 18, "neto", "casinha", "Ze@gmail.com",1,1);
        System.out.println(aluno.getIdAluno()+" "+aluno.getIdade()+" "+aluno.getNome()+
        " "+aluno.getEndereco()+" "+aluno.getEmail()+" "+aluno.getIdCurso()+" "+aluno.getIdTurma());

        Disciplina disciplina = new Disciplina(1, "70", "DS2");
        System.out.println(disciplina.getId_disciplina()+" "+disciplina.getCarga_horaria()+ " "+
        disciplina.getNome_disciplina());

        Turma turma = new Turma("3 periodo", 10, 1, 1, 1, 1);
        System.out.println(turma.getIdTurma()+" "+turma.getQtdAlunos()+" "+turma.getTurmaNome()+" "+
        turma.getIdAluno()+" "+turma.getIdDisciplina()+" "+turma.getIdCurso());

        Professor professor = new Professor("Fabio", "Fabio@gmail.com",
        "Rua tal do sla o q", 3, 25, "998085748",1,1);
        System.out.println(professor.getIdProfessor()+" "+professor.getNomeProfessor()+" "+professor.getIdadeProf()+
        " "+professor.getEmailProf()+" "+professor.getEnderecoProf()+" "+professor.getTelefoneProf()+
        " "+professor.getIdCurso()+" "+professor.getIdTurma());

        Curso curso = new Curso(1, 120, 1, 1, "ADS");
        System.out.println(curso.getIdCurso()+" "+curso.getCargaHoraria()+" "+curso.getQtdAlunos()+" "+
        curso.getQtdProfessores()+" "+curso.getNomeCurso());
    }
}
