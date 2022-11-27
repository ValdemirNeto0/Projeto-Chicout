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

public class TurmaDAO {
  public Optional<Turma> get(long id) {
    String sql = "SELECT * FROM Turma WHERE idTurma = " + id;
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Turma turmas = new Turma();
    try {
      con = Conection.getConnection();
      statement = con.prepareStatement(sql);
      resultSet = statement.executeQuery();
      turmas.setIdTurma(resultSet.getInt("idTurma"));
      turmas.setTurmaNome(resultSet.getString("NomeTurma"));
      turmas.setQtdAlunos(resultSet.getInt("qtdTurma"));
      } catch (Exception ex) {
        try {
          throw new SQLException("Erro ao procurar a Turma!"+ ex.getMessage(), ex);
        } catch (SQLException ex1) {
          Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex1);
        }
      } finally {
        Conection.closeConnection(con, statement, resultSet);
      }
      return Optional.of(turmas);
    }

    public List<Turma> getAll() {
      String sql = "SELECT * FROM Turma";
      Connection con = null;
      PreparedStatement statement = null;
      ResultSet resultSet = null;
      List<Turma> TurmaList = new ArrayList<Turma>();
      try {
        con = Conection.getConnection();
        statement = con.prepareStatement(sql);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
          Turma turma = new Turma();
          turma.setIdTurma(resultSet.getInt("idTurma"));
          turma.setTurmaNome(resultSet.getString("NomeTurma"));
          turma.setQtdAlunos(resultSet.getInt("QtdTurma"));
          TurmaList.add(turma);
        }
      } catch (Exception ex) {
        try {
          throw new SQLException("Erro ao Listar as Turmas!"+ ex.getMessage(), ex);
        } catch (SQLException ex1) {
          Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex1);
        }
      } finally {
        Conection.closeConnection(con, statement, resultSet);
      }
      return TurmaList;
    }
    
    public void save (Turma turmas) {
      String sql = "INSERT INTO turmas (idTurma,nomeTurma,QtdAlunos,idCurso,idAluno,idDisciplina) VALUES (?,?,?,?,?,?)";
      Connection con = null;
      PreparedStatement statement = null;
      try {
        con = Conection.getConnection();
        statement = con.prepareStatement(sql);
        statement.setString(1, turmas.getTurmaNome());
        statement.setInt(2, turmas.getIdTurma());
        statement.setInt(3,turmas.getQtdAlunos());
        statement.setInt(4, turmas.getIdCurso());
        statement.setInt(5, turmas.getIdAluno());
        statement.setInt(6, turmas.getIdDisciplina());
        statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar Turma!"+ ex.getMessage(), ex);
        } finally {
            Conection.closeConnection(con, statement);
        }
    }
    
    public void update(Turma turma, String[] params) {
      String sql = "UPDATE turma SET idTurma = ?, nomeTurma= ?, qtdAlunos = ?, idCurso = ?, idAluno = ?, idDisciplina = ?, WHERE id_turma = ?" ;
      Connection con = null;
      PreparedStatement statement = null;
      for( int i = 0; i < 7; i++){
        if(params[i] == null){
          switch (i){
            case 0: 
            params[i] = turma.getTurmaNome();
            break;
            case 1:
            params[i] = Integer.toString(turma.getIdTurma());
            break;
            case 2:
            params[i] = Integer.toString(turma.getQtdAlunos());
            break;
            case 3:
            params[i] = Integer.toString(turma.getIdCurso());
            break;
            case 4:
            params[i] = Integer.toString(turma.getIdAluno());
            break;
            case 5:
            params[i] = Integer.toString(turma.getIdDisciplina());
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
          statement.setInt(7, turma.getIdTurma());//possivel erro, olhar no teste!!!
          statement.execute();
          } catch (Exception ex) {
            throw new RuntimeException("Erro ao alterar tarefa " + ex.getMessage(), ex);
          } finally {
            Conection.closeConnection(con, statement);
          }
      }
    }
    
    public void delete(Turma turma) {
      String sql = "DELETE FROM turma WHERE idTurma = ?";
      Connection con = null;
      PreparedStatement statement = null;
      try {
        con = Conection.getConnection();
        statement = con.prepareStatement(sql);
        Conection.getConnection();
        statement.setInt(1, turma.getIdTurma());
        statement.execute();
        } catch (Exception ex) {
          try {
            throw new SQLException("Erro ao deletar a Turma!"+ ex.getMessage(), ex);
          } catch (SQLException ex1) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex1);
          }
        } finally {
          Conection.closeConnection(con, statement);
        }
    }
}