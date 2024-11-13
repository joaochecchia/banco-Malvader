package dao;

import model.Transacao;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransacaoDAO {

    public void depositoDAO(Double valor, int idConta){
        String sqlTransacao = "INSERT INTO transacao(tipo_transacao, valor, id_conta)" +
                "VALUES (?, ?, ?)";
        String sqlConta = "UPDATE conta SET saldo = saldo + ? WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmtTansacao = conn.prepareStatement(sqlTransacao);
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);

            stmtTansacao.setString(1, "DEPOSITO");
            stmtTansacao.setDouble(2, valor);
            stmtTansacao.setInt(3, idConta);

            stmtConta.setDouble(1, valor);
            stmtConta.setInt(2, idConta);

            stmtTansacao.executeUpdate();
            stmtConta.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean saqueDAO(Double valor, int idConta){
        String sqlVerificarSaldo = "SELECT * FROM conta WHERE id_conta = ?";
        String sqlTransacao = "INSERT INTO transacao (tipo_transacao, valor, id_conta)" +
                "VALUES(?, ?, ?)";
        String sqlConta = "UPDATE conta SET saldo = saldo - ? WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmtVerificarSaldo = conn.prepareStatement(sqlVerificarSaldo);

            stmtVerificarSaldo.setInt(1, idConta);
            ResultSet rsSaldo = stmtVerificarSaldo.executeQuery();

            rsSaldo.next();
            int saldo = rsSaldo.getInt("saldo");

            if(valor <= saldo){
                PreparedStatement stmtTransacao = conn.prepareStatement(sqlTransacao);
                PreparedStatement stmtConta = conn.prepareStatement(sqlConta);

                stmtTransacao.setString(1, "SAQUE");
                stmtTransacao.setDouble(2, valor);
                stmtTransacao.setDouble(3, idConta);

                stmtConta.setDouble(1, valor);
                stmtConta.setDouble(2, idConta);

                stmtConta.executeUpdate();
                stmtTransacao.executeUpdate();

                return true;
            } else{
                return false;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Transacao> extratoDAO(int idConta){
        String sql = "SELECT * FROM transacao WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idConta);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Transacao> transacoes = new ArrayList<>();
            while (rs.next()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                Transacao transacao = new Transacao(
                        rs.getInt(1), rs.getString(2), rs.getDouble(3),
                        LocalDateTime.parse(rs.getString(4), formatter)
                );

                transacoes.add(transacao);
            }

            return transacoes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
