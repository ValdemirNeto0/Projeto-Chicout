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


public class ProfessorDAO {
    public Optional<Professor> get(long id) {
        String sql = "SELECT * FROM professores WHERE idProfessores = " + id;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Professor professores = new Professor();
        try {
          con = Conection.getConnection();
          statement = con.prepareStatement(sql);
          resultSet = statement.executeQuery();
    
          professores.setIdProfessor(resultSet.getInt("idProfessor"));
          professores.setNomeProfessor(resultSet.getString("Nome"));
          professores.setEnderecoProf(resultSet.getString("endereco"));
          professores.setEmailProf(resultSet.getString("emailProf"));
          professores.setTelefoneProf(resultSet.getString("TelefoneProf"));
          professores.setIdadeProf(resultSet.getInt("Idade"));
    
        } catch (Exception ex) {
          try {
            throw new SQLException("Erro ao procurar o professor "
                + ex.getMessage(), ex);
          } catch (SQLException ex1) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex1);
          }
        } finally {
          Conection.closeConnection(con, statement, resultSet);
        }
        return Optional.of(professores);
      }
    
      public List<Professor> getAll() {
    
        String sql = "SELECT * FROM professores";
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Professor> professoresList = new ArrayList<Professor>();
        try {
          con = Conection.getConnection();
          statement = con.prepareStatement(sql);
          resultSet = statement.executeQuery();
          while (resultSet.next()) {
            Professor professores = new Professor();
            professores.setIdProfessor(resultSet.getInt("idProfessores"));
            professores.setNomeProfessor(resultSet.getString("Nome"));
            professores.setEnderecoProf(resultSet.getString("endereco"));
            professores.setEmailProf(resultSet.getString("emailProf"));
            professores.setTelefoneProf(resultSet.getString("telefoneProf"));
            professores.setIdadeProf(resultSet.getInt("Idade"));
            professoresList.add(professores);
          }
        } catch (Exception ex) {
          try {
            throw new SQLException("Erro ao Listar os professores "
                + ex.getMessage(), ex);
          } catch (SQLException ex1) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex1);
          }
        } finally {
          Conection.closeConnection(con, statement, resultSet);
        }
        return professoresList;
      }
    
      public void save(Professor professores) {
        String sql = "INSERT INTO professores(NomeProfessor,EmailProf,EnderecoProf,telefoneProf,idTurma,idCurso) VALUES (?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement statement = null;
    
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            statement.setString(1, professores.getNomeProfessor());
            statement.setString(2, professores.getEnderecoProf());
            statement.setString(3, professores.getEmailProf());
            statement.setString(4, professores.getTelefoneProf());
            statement.setInt(5, professores.getIdadeProf());
            statement.setInt(6, professores.getIdTurma());
            statement.setInt(7, professores.getIdCurso());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar Professor!" + ex.getMessage(), ex);
        } finally {
            Conection.closeConnection(con, statement);
        }
      }
    
      public void update(Professor professores, String[] params) {
        String sql = "UPDATE professores SET nome = ?, endereco = ?, email_Professores = ?, "
            + "celular = ?, telefone = ?, id_turma = ?, id_curso = ? WHERE id_Professores = ?";
    
        Connection con = null;
        PreparedStatement statement = null;
    
        for (int i = 0; i < 8; i++) {
          if (params[i] == null) {
            switch (i) {
              case 0:
                params[i] = professores.getNomeProfessor();
                break;
              case 1:
                params[i] = professores.getEnderecoProf();
                break;
              case 2:
                params[i] = professores.getEmailProf();
                break;
              case 3:
                params[i] = professores.getTelefoneProf();
                break;
              case 4:
                params[i] = Integer.toString(professores.getIdadeProf());
                break;
              case 5:
                params[i] = Integer.toString(professores.getIdTurma());
                break;
              case 6:
                params[i] = Integer.toString(professores.getIdCurso());
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
            statement.setInt(7, Integer.parseInt(params[6]));
            statement.setInt(8, professores.getIdProfessor());
            statement.execute();
          } catch (Exception ex) {
            throw new RuntimeException("Erro ao alterar tarefa " + ex.getMessage(), ex);
          } finally {
            Conection.closeConnection(con, statement);
          }
        }
      }
    
      public void delete(Professor professores) {
        String sql = "DELETE FROM professores WHERE idProfessor = ?";
    
        Connection con = null;
    
        PreparedStatement statement = null;
        try {
          con = Conection.getConnection();
          statement = con.prepareStatement(sql);
          Conection.getConnection();
          statement.setInt(1, professores.getIdProfessor());
          statement.execute();
        } catch (Exception ex) {
          try {
            throw new SQLException("Erro ao deletar o professor!" + ex.getMessage(), ex);
          } catch (SQLException ex1) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex1);
          }
        } finally {
          Conection.closeConnection(con, statement);
        }
      }
}
