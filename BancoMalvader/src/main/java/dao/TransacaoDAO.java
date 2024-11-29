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
        //Strings sql genéricas para inserir
        String sqlTransacao = "INSERT INTO transacao(tipo_transacao, valor, id_conta)" +
                "VALUES (?, ?, ?)";
        String sqlConta = "UPDATE conta SET saldo = saldo + ? WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){ //tente conectar
            //criação da classe PreparedStatement
            PreparedStatement stmtTansacao = conn.prepareStatement(sqlTransacao);
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);

            //preparação da String
            stmtTansacao.setString(1, "DEPOSITO");
            stmtTansacao.setDouble(2, valor);
            stmtTansacao.setInt(3, idConta);

            stmtConta.setDouble(1, valor);
            stmtConta.setInt(2, idConta);

            //inserção na tabela
            stmtTansacao.executeUpdate();
            stmtConta.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();//printar o erro
        }
    }

    public boolean saqueDAO(Double valor, int idConta){
        //sql generico
        String sqlVerificarSaldo = "SELECT * FROM conta WHERE id_conta = ?";
        String sqlTransacao = "INSERT INTO transacao (tipo_transacao, valor, id_conta)" +
                "VALUES(?, ?, ?)";
        String sqlConta = "UPDATE conta SET saldo = saldo - ? WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            //verificar se o sque pode ser efetuado
            PreparedStatement stmtVerificarSaldo = conn.prepareStatement(sqlVerificarSaldo);

            stmtVerificarSaldo.setInt(1, idConta);
            ResultSet rsSaldo = stmtVerificarSaldo.executeQuery();

            rsSaldo.next();
            int saldo = rsSaldo.getInt("saldo");

            if(valor <= saldo){
                //prepara as strings
                PreparedStatement stmtTransacao = conn.prepareStatement(sqlTransacao);
                PreparedStatement stmtConta = conn.prepareStatement(sqlConta);

                //setar os parametros
                stmtTransacao.setString(1, "SAQUE");
                stmtTransacao.setDouble(2, valor);
                stmtTransacao.setDouble(3, idConta);

                stmtConta.setDouble(1, valor);
                stmtConta.setDouble(2, idConta);

                //atualizar a tabela
                stmtConta.executeUpdate();
                stmtTransacao.executeUpdate();

                //retorna se saldo foi efetuado
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
        //String genérica
        String sql = "SELECT * FROM transacao WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idConta);

            //classe que armazena a consulta
            ResultSet rs = stmt.executeQuery();

            //arrayList que armazena as transações
            ArrayList<Transacao> transacoes = new ArrayList<>();
            //enqaunto tiver colunas relacionadas ao id da conta
            while (rs.next()){
                //Formato do LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                //criar classe transacao
                Transacao transacao = new Transacao(
                        rs.getInt(1), rs.getString(2), rs.getDouble(3),
                        LocalDateTime.parse(rs.getString(4), formatter)
                );

                //colocar no ArrayList
                transacoes.add(transacao);
            }

            return transacoes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
