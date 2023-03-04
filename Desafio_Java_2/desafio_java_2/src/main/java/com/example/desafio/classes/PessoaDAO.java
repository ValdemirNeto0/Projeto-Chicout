package main.java.com.example.desafio.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAO implements DAO<Pessoa>{
    @Override
    public Optional<Pessoa> get(int ID){
        String sql = "SELECT * FROM Pessoas WHERE ID = " + ID;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Pessoa pessoa = new Pessoa();
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            resultSet = statement.executeQuery();
            pessoa.setID(resultSet.getID("ID"));
            pessoa.setNome(resultSet.getString("Nome"));
            pessoa.setIdade(resultSet.getInt("Idade"));
            pessoa.setPosFila(resultSet.getInt("PosFila"));

        } catch (Exception ex) {
            try {
                throw new SQLException("Erro, Pessoa não encontrada!"+ ex.getMessage(), ex);
            } catch (SQLException ex1) {
                Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            Conection.closeConnection(con, statement, resultSet);
        }
        return Optional.of(pessoa);
    }

    @Override
    public List<Pessoa> getAll() {
        String sql = "SELECT * FROM Pessoas";
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Pessoa> PessoasList = new ArrayList<Pessoa>();
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setID(resultSet.getID("ID"));
                pessoa.setNome(resultSet.getString("Nome"));
                pessoa.setIdade(resultSet.getInt("Idade"));
                pessoa.setPosFila(resultSet.getInt("PosFila"));
                PessoasList.add(pessoa);
            }
        } catch (Exception ex) {
            try {
                throw new SQLException("Erro ao exibir listagem"+ ex.getMessage(), ex);
            } catch (SQLException ex1) {
                Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            Conection.closeConnection(con, statement, resultSet);
        }
        return PessoasList;
    }

    @Override
    public void save(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoas (nome,idade,PosFila) VALUES (?,?,?)";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, pessoa.getID());
            statement.setString(1, pessoa.getNome());
            statement.setInt(2, pessoa.getIdade());
            statement.setInt(3, pessoa.getIdTurma());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar pessoa!"+ ex.getMessage(), ex);
        } finally {
            Conection.closeConnection(con, statement);
        }
    }

    @Override
    public void update(Pessoa pessoa, String[] params) {
        String sql = "UPDATE Pessoas SET nome = ?, idade = ?, posFila = ? WHERE ID = ?" ;
        Connection con = null;
        PreparedStatement statement = null;
        for( int i = 0; i < 2; i++){
            if(params[i] == null){
                switch (i){
                    case 0: 
                    params[i] = pessoa.getNome();
                    break;
                    case 1:
                    params[i] = pessoa.getIdade();
                    break;
                    case 2:
                    params[i] = pessoa.getPosFila();
                    break;
                }
            }
            try {
                con = Conection.getConnection();
                statement = con.prepareStatement(sql);
                statement.setString(1, params[0]);
                statement.setInt(2, Integer.parseInt(params[1]));
                statement.setInt(3, Integer.parseInt(params[2]));
                statement.setInt(4, pessoa.getID(params[3]));
                statement.execute();
            } catch (Exception ex) {
                throw new RuntimeException("Erro no update!" + ex.getMessage(), ex);
            } finally {
                Conection.closeConnection(con, statement);
            }
        }
    }

    @Override
    public void delete(Pessoa pessoa) {
        String sql = "DELETE FROM Pessoas WHERE ID = ?";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = Conection.getConnection();
            statement = con.prepareStatement(sql);
            Conection.getConnection();
            statement.setInt(1, pessoa.getID());
            statement.execute();
        } catch (Exception ex) {
            try {
                throw new SQLException("Erro ao deletar o Pessoa!"+ ex.getMessage(), ex);
            } catch (SQLException ex1) {
                Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            Conection.closeConnection(con, statement);
        }
    }
}
