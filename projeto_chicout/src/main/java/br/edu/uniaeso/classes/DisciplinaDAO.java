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

public class DisciplinaDAO {
    public Optional<Disciplina> get(long id) {
        String sql = "SELECT * FROM discplinas WHERE idDisciplina = " + id;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Disciplina disciplinas = new Disciplina();
        try {
          con = Conection.getConnection();
          statement = con.prepareStatement(sql);
          resultSet = statement.executeQuery();
          disciplinas.setIdDisciplina(resultSet.getInt("idDisciplina"));
          disciplinas.setNomeDisciplina(resultSet.getString("nomeDisciplina"));
          disciplinas.setCargaHoraria(resultSet.getString("cargaHoraria"));
          disciplinas.setIdProfessor(resultSet.getInt("idProfessor"));
        } catch (Exception ex) {
          try {
            throw new SQLException("Erro ao procurar o disciplina!"+ ex.getMessage(), ex);
          } catch (SQLException ex1) {
              Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
          } finally {
            Conection.closeConnection(con, statement, resultSet);
          }
          return Optional.of(disciplinas);
        }
        
        public List<Disciplina> getAll() {
          String sql = "SELECT * FROM disciplinas";
          Connection con = null;
          PreparedStatement statement = null;
          ResultSet resultSet = null;
          List<Disciplina> disciplinasList = new ArrayList<Disciplina>();
          try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Disciplina disciplinas = new Disciplina();
                disciplinas.setIdDisciplina(resultSet.getInt("idDisciplina"));
                disciplinas.setNomeDisciplina(resultSet.getString("nomeDisciplina"));
                disciplinas.setCargaHoraria(resultSet.getString("cargaHoraria"));
                disciplinas.setIdProfessor(resultSet.getInt("idProfessor"));
                disciplinasList.add(disciplinas);
            }
          } catch (Exception ex) {
            try {
              throw new SQLException("Erro ao Listar os disciplinas!"+ ex.getMessage(), ex);
            } catch (SQLException ex1) {
              Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
          } finally {
            Conection.closeConnection(con, statement, resultSet);
          }
          return disciplinasList;
        }
        
        public void save(Disciplina disciplinas) {
          String sql = "INSERT INTO disciplinas(nomeDisciplina,cargaHoraria,idProfessor) VALUES (?,?,?)";
          Connection con = null;
          PreparedStatement statement = null;
          try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            statement.setString(1, disciplinas.getNomeDisciplina());
            statement.setString(2, disciplinas.getCargaHoraria());
            statement.setInt(3, disciplinas.getIdProfessor());
            statement.execute();
          } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar Disciplina!" + ex.getMessage(), ex);
          } finally {
            Conection.closeConnection(con, statement);
          }
        }
        
        public void update(Disciplina disciplinas, String[] params) {
          String sql = "UPDATE disciplinas SET nomeDisciplina = ?, cargaHoraria = ?, idProfessor = ? WHERE idDisciplina = ?";
          Connection con = null;
          PreparedStatement statement = null;
          for (int i = 0; i < 3; i++) {
            if (params[i] == null) {
              switch (i) {
                case 0:
                  params[i] = disciplinas.getNomeDisciplina();
                  break;
                case 1:
                  params[i] = disciplinas.getCargaHoraria();
                  break;
                case 2:
                  params[i] = Integer.toString(disciplinas.getIdProfessor());
                  break;
              }
            }
            try {
              con = Conection.getConnection();
              statement = con.prepareStatement(sql);  
              statement.setString(1, params[0]);
              statement.setString(2, params[1]);
              statement.setString(3, params[2]);
              statement.setInt(4, disciplinas.getIdDisciplina());
              statement.execute();
            } catch (Exception ex) {
              throw new RuntimeException("Erro ao alterar Disciplina!" + ex.getMessage(), ex);
            } finally {
              Conection.closeConnection(con, statement);
            }
          }
        }
        
        public void delete(Disciplina disciplinas) {
          String sql = "DELETE FROM disciplinas WHERE idDisciplina = ?";
          Connection con = null;
          PreparedStatement statement = null;
          try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            Conection.getConnection();
            statement.setInt(1, disciplinas.getIdDisciplina());
            statement.execute();
          } catch (Exception ex) {
            try {
              throw new SQLException("Erro ao deletar o Disciplina!" + ex.getMessage(), ex);
            } catch (SQLException ex1) {
              Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
          } finally {
            Conection.closeConnection(con, statement);
          }
        }
}
