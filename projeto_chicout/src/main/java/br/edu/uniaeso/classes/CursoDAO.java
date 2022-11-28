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

public class CursoDAO {
  public Optional<Curso> get(long id) {
    String sql = "SELECT * FROM cursos WHERE idCurso = " + id;
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Curso cursos = new Curso();
    try {
      con = Conection.getConnection();
      statement = con.prepareStatement(sql);
      resultSet = statement.executeQuery();
      cursos.setIdCurso(resultSet.getInt("IdCurso"));
      cursos.setCargaHoraria(resultSet.getInt("CargaHoraria"));
      cursos.setQtdAlunos(resultSet.getInt("qtdAlunos"));
      cursos.setQtdProfessores(resultSet.getInt("qtdProfessores"));
      cursos.setNomeCurso(resultSet.getString("nomeCurso"));
    } catch (Exception ex) {
      try {
        throw new SQLException("Erro ao procurar o curso!"+ ex.getMessage(), ex);
      } catch (SQLException ex1) {
        Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex1);
      }
    } finally {
      Conection.closeConnection(con, statement, resultSet);
    }
    return Optional.of(cursos);
  }
        
  public List<Curso> getAll() {
    String sql = "SELECT * FROM cursos";
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    List<Curso> professoresList = new ArrayList<Curso>();
    try {
      con = Conection.getConnection();
      statement = con.prepareStatement(sql);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Curso cursos = new Curso();
        cursos.setIdCurso(resultSet.getInt("idCurso"));
        cursos.setNomeCurso(resultSet.getString("nomeCurso"));
        cursos.setCargaHoraria(resultSet.getInt("cargaHoraria"));
        cursos.setQtdAlunos(resultSet.getInt("qtdAlunos"));
        cursos.setQtdProfessores(resultSet.getInt("qtdProfessores"));
        professoresList.add(cursos);
      }
    } catch (Exception ex) {
      try {
        throw new SQLException("Erro ao Listar os cursos!"+ ex.getMessage(), ex);
      } catch (SQLException ex1) {
        Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex1);
      }
    } finally {
      Conection.closeConnection(con, statement, resultSet);
    }
    return professoresList;
  }
        
  public void save(Curso cursos) {
    String sql = "INSERT INTO cursos(nomeCurso,cargaHoraria,qtdAlunos,qtdProfessores) VALUES (?,?,?,?)";
    Connection con = null;
    PreparedStatement statement = null;
    try {
      con = Conection.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, cursos.getNomeCurso());
      statement.setInt(2, cursos.getCargaHoraria());
      statement.setInt(3, cursos.getQtdAlunos());
      statement.setInt(4, cursos.getQtdProfessores());
      statement.execute();
    } catch (SQLException ex) {
      throw new RuntimeException("Erro ao salvar Curso!" + ex.getMessage(), ex);
    } finally {
      Conection.closeConnection(con, statement);
    }
  }
        
  public void update(Curso cursos, String[] params) {
    String sql = "UPDATE cursos SET nomeCurso = ?, cargaHoraria = ?, qtdAlunos = ?, qtdProfessores = ? WHERE idCurso = ?";
    Connection con = null;
    PreparedStatement statement = null;
    for (int i = 0; i < 6; i++) {
      if (params[i] == null) {
        switch (i) {
          case 0:
            params[i] = cursos.getNomeCurso();
            break;
          case 1:
            params[i] = Integer.toString(cursos.getCargaHoraria());
            break;
          case 2:
            params[i] = Integer.toString(cursos.getQtdAlunos());
            break;
          case 3:
            params[i] = Integer.toString(cursos.getQtdProfessores());
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
        statement.setInt(5, cursos.getIdCurso());
        statement.execute();
      } catch (Exception ex) {
        throw new RuntimeException("Erro ao alterar Curso!" + ex.getMessage(), ex);
      } finally {
        Conection.closeConnection(con, statement);
      }
    }
  }
        
  public void delete(Curso cursos) {
    String sql = "DELETE FROM cursos WHERE idCurso = ?";
    Connection con = null;
    PreparedStatement statement = null;
    try {
      con = Conection.getConnection();
      statement = con.prepareStatement(sql);
      Conection.getConnection();
      statement.setInt(1, cursos.getIdCurso());
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