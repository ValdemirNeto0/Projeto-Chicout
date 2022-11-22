package br.edu.uniaeso.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.edu.uniaeso.Conector.*;


public class AlunoDao implements Dao<Aluno>{
    @Override
    public Optional<Aluno> get(long id) {
        String sql = "SELECT * FROM alunos WHERE id_Aluno = " + id;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Aluno alunos = new Aluno();
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            resultSet = statement.executeQuery();
           
            alunos.setIdAluno(resultSet.getInt("idAluno"));
            alunos.setNome(resultSet.getString("Nome"));
            alunos.setEndereco(resultSet.getString("Endereco"));
            alunos.setEmail(resultSet.getString("Email"));
            alunos.setIdade(resultSet.getInt("Idade"));

        } catch (Exception ex) {
            try {
                throw new SQLException("Erro ao procurar o aluno "
                        + ex.getMessage(), ex);
            } catch (SQLException ex1) {
                Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            Conection.closeConnection(con, statement, resultSet);
        }
        return Optional.of(alunos);
    }

    @Override
    public List<Aluno> getAll() {
        String sql = "SELECT * FROM alunos";
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Aluno> alunosList = new ArrayList<Aluno>();
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Aluno alunos = new Aluno();
                alunos.setIdAluno(resultSet.getInt("id_Aluno"));
                alunos.setNome(resultSet.getString("Nome"));
                alunos.setEndereco(resultSet.getString("endereco"));
                alunos.setEmail(resultSet.getString("email_Aluno"));
                alunos.setIdade(resultSet.getInt("Idade"));
                alunosList.add(alunos);
            }
        } catch (Exception ex) {
            try {
                throw new SQLException("Erro ao Listar os alunos "+ ex.getMessage(), ex);
            } catch (SQLException ex1) {
                Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            Conection.closeConnection(con, statement, resultSet);
        }
        return alunosList;
    }

    @Override
    public void save(Aluno alunos) {
        String sql = "INSERT INTO alunos (nome,endereco,email_Aluno,celular,"
        + "telefone,id_Turma,id_Curso) VALUES (?,?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            statement.setString(1, alunos.getNome());
            statement.setString(2, alunos.getEndereco());
            statement.setString(3, alunos.getEmail());
            statement.setInt(4, alunos.getIdade());
            statement.setInt(6, alunos.getIdTurma());
            statement.setInt(7, alunos.getIdCurso());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar Aluno "
                    + ex.getMessage(), ex);
        } finally {
            Conection.closeConnection(con, statement);
        }
    }

    /* (non-Javadoc)
     * @see DAO.DAO#update(java.lang.Object, java.lang.String[])
     */
    @Override
    public void update(Aluno alunos, String[] params) {
        String sql = "UPDATE alunos SET nome = ?, endereco = ?, email_Aluno = ?, "
        + "celular = ?, telefone = ?, id_turma = ?, id_curso = ? WHERE id_Aluno = ?" ;

        Connection con = null;
        PreparedStatement statement = null;


        for( int i = 0; i < 7; i++){
            if(params[i] == null){
                switch (i){
                    case 0: 
                    params[i] = alunos.getNome();
                    break;
                    case 1:
                    params[i] = alunos.getEndereco();
                    break;
                    case 2:
                    params[i] = alunos.getEmail();
                    break;
                    case 3:
                    params[i] = Integer.toString(alunos.getIdade());
                    break;
                    case 4:
                    params[i] = Integer.toString(alunos.getIdTurma());
                    break;
                    case 5:
                    params[i] = Integer.toString(alunos.getIdCurso());
                    break;
            }
        }
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);

            statement.setString(1, params[0]);
            statement.setString(2, params[1]);
            statement.setString(3, params[2]);
            statement.setString(4, params[3]);
            statement.setString(5, params[4]);
            statement.setInt(6, Integer.parseInt(params[5]));
            statement.setInt(7, alunos.getIdAluno());
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao alterar tarefa " + ex.getMessage(), ex);
        } finally {
            Conection.closeConnection(con, statement);
        }
    }
    }

    @Override
    public void delete(Aluno alunos) {
        String sql = "DELETE FROM alunos WHERE id_aluno = ?";

        Connection con = null;

        PreparedStatement statement = null;
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            Conection.getConnection();
            statement.setInt(1, alunos.getIdAluno());
            statement.execute();
        } catch (Exception ex) {
            try {
                throw new SQLException("Erro ao deletar o aluno "
                        + ex.getMessage(), ex);
            } catch (SQLException ex1) {
                Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            Conection.closeConnection(con, statement);
        }
    }
}