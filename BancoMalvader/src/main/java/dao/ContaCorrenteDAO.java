package dao;

import model.*;
import util.Conexao;
import util.GerarNumeroConta;
import util.GerarAgencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ContaCorrenteDAO {
    public void registrarContaCorrente(ContaCorrente conta, Cliente cliente){
        //string sql generica
        String sqlConta = "INSERT INTO conta(numero_conta, agencia, saldo, tipo_conta, id_cliente)" +
                "values (?, ?, ?, ?, ?)";

        String sqlCorrente = "INSERT INTO conta_corrente(limite, data_vencimento, id_conta)" +
                "values(?, ?, ?)";

        try(Connection conn = Conexao.conexao()){//Conexão
            //Gerar a agencai e numero com as classes na Util
            GerarNumeroConta numeroConta = new GerarNumeroConta();
            GerarAgencia numeroAgencia = new GerarAgencia();

            //prepara a string
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtCorrente = conn.prepareStatement(sqlCorrente);

            stmtConta.setString(1, numeroConta.gerarNumero("CORRENTE"));//gera numero pelo tipo de conta do cliente
            stmtConta.setString(2, numeroAgencia.gerarAgencia(cliente.getEndereco().getEstado()));//gera a agencia pelo estado do cliente
            stmtConta.setDouble(3, conta.getSaldo());
            stmtConta.setString(4, "CORRENTE");
            stmtConta.setInt(5, cliente.getIdCliente());

            //executa a inserção
            stmtConta.executeUpdate();
            ResultSet rs = stmtConta.getGeneratedKeys();

            //pega O ID da conta para inserir a poupança
            int idconta = 0;
            if (rs.next()) {
                idconta = rs.getInt(1);
            }
            rs.close();

            //prepara a string da conta corrente
            stmtCorrente.setDouble(1, conta.getLimite());
            stmtCorrente.setString(2, conta.getDataVencimento().toString());
            stmtCorrente.setInt(3, idconta);

            //inseri na tabela conta_corrente
            stmtCorrente.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ContaCorrente getContaCorrente(ResultSet rs, Cliente cliente) {
        try (Connection conn = Conexao.conexao()) {
            //Stting sql genrica sendo preparada
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM conta_corrente WHERE id_conta = ?");
            stmt.setInt(1, rs.getInt("id_conta"));

            //ResultSet com s pesquisa
            ResultSet rsCorrente = stmt.executeQuery();

            //Se tiver um correspondente
            if (rsCorrente.next()) {
                //retorne a classe conta Corrente
                LocalDate dataVencimento = LocalDate.parse(rsCorrente.getString("data_vencimento"));
                return new ContaCorrente(
                        rs.getString("numero_conta"),
                        rs.getString("agencia"),
                        rs.getDouble("saldo"),
                        cliente,
                        rsCorrente.getDouble("limite"),
                        dataVencimento
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editarParaContaCorrente(int idConta, double limite, LocalDate vencimento){
        //String sql genérica
        String sql = "INSERT INTO conta_corrente(limite, data_vencimento, id_conta) VALUES (?, ?, ?);";

        try(Connection conn = Conexao.conexao()){
            //preparação da string para a consulta
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, limite);
            stmt.setString(2, vencimento.toString());
            stmt.setInt(3, idConta);

            //executa a edição
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarContaCorrente(int idConta){
        //string sql generica
        String sqlCorrente = "DELETE FROM conta_corrente WHERE id_conta = ?";
        String sqlConta = "DELETE FROM conta WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            //preparação das strings
            PreparedStatement stmtCorrente = conn.prepareStatement(sqlCorrente);
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);

            stmtCorrente.setInt(1, idConta);
            //update na tabela conta_corrente
            stmtCorrente.executeUpdate();

            stmtConta.setInt(1, idConta);
            //update na tabela conta
            stmtConta.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
