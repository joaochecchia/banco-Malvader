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

        String sqlConta = "INSERT INTO conta(numero_conta, agencia, saldo, tipo_conta, id_cliente)" +
                "values (?, ?, ?, ?, ?)";

        String sqlCorrente = "INSERT INTO conta_corrente(limite, data_vencimento, id_conta)" +
                "values(?, ?, ?)";

        try(Connection conn = Conexao.conexao()){
            GerarNumeroConta numeroConta = new GerarNumeroConta();
            GerarAgencia numeroAgencia = new GerarAgencia();

            PreparedStatement stmtConta = conn.prepareStatement(sqlConta, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtCorrente = conn.prepareStatement(sqlCorrente);

            stmtConta.setString(1, numeroConta.gerarNumero("CORRENTE"));
            stmtConta.setString(2, numeroAgencia.gerarAgencia(cliente.getEndereco().getEstado()));
            stmtConta.setDouble(3, conta.getSaldo());
            stmtConta.setString(4, "CORRENTE");
            stmtConta.setInt(5, cliente.getIdCliente());

            stmtConta.executeUpdate();
            ResultSet rs = stmtConta.getGeneratedKeys();

            int idconta = 0;
            if (rs.next()) {
                idconta = rs.getInt(1);
            }
            rs.close();

            stmtCorrente.setDouble(1, conta.getLimite());
            stmtCorrente.setString(2, conta.getDataVencimento().toString());
            stmtCorrente.setInt(3, idconta);

            stmtCorrente.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ContaCorrente getContaCorrente(ResultSet rs, Cliente cliente) {
        try (Connection conn = Conexao.conexao()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM conta_corrente WHERE id_conta = ?");
            stmt.setInt(1, rs.getInt("id_conta"));

            ResultSet rsCorrente = stmt.executeQuery();

            if (rsCorrente.next()) {
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

    public void editarContaPoupanca(ContaCorrente conta, int idConta){
        String sqlConta = "UPDATE conta SET numero_conta = ?, agencia = ?, saldo = ? " +
                "WHERE id_conta = ?";
        String sqlContaCorrente = "UPDATE conta_poupanca SET limite = ?, data_vencimento = ?" +
                "WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);
            PreparedStatement stmtCorrente = conn.prepareStatement(sqlContaCorrente);

            ContaDAO contaDao = new ContaDAO();

            stmtConta.setString(1, conta.getNumeroConta());
            stmtConta.setString(2, conta.getAgencia());
            stmtConta.setDouble(3, conta.getSaldo());
            stmtConta.setInt(4, idConta);

            stmtConta.executeUpdate();

            stmtCorrente.setDouble(1, conta.getLimite());
            stmtCorrente.setString(2, conta.getNumeroConta().toString());
            stmtCorrente.setInt(3, idConta);

            stmtCorrente.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarContaCorrente(Conta conta){

        String sqlCorrente = "DELETE FROM conta_corrente WHERE id_conta = ?";
        String sqlConta = "DELETE FROM conta WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmtCorrente = conn.prepareStatement(sqlCorrente);
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);

            ContaDAO contaDAO = new ContaDAO();
            int idConta = contaDAO.getIDConta(conta.getNumeroConta());

            stmtCorrente.setInt(1, idConta);
            stmtCorrente.executeUpdate();

            stmtConta.setInt(1, idConta);
            System.out.println("ID conta: " + idConta);
            stmtConta.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
