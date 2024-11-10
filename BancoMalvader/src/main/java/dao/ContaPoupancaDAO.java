package dao;

import model.Cliente;
import model.Conta;
import model.ContaPoupanca;
import util.Conexao;
import util.GerarAgencia;
import util.GerarNumeroConta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaPoupancaDAO {
    public void registrarContaPoupanca(ContaPoupanca conta, Cliente cliente){

        String sqlConta = "INSERT INTO conta(numero_conta, agencia, saldo, tipo_conta, id_cliente)" +
                "values (?, ?, ?, ?, ?)";

        String sqlPoupanca = "INSERT INTO conta_poupanca(taxa_rendimento, id_conta)" +
                "values(?, ?)";

        try(Connection conn = Conexao.conexao()){
            GerarNumeroConta numeroConta = new GerarNumeroConta();
            GerarAgencia numeroAgencia = new GerarAgencia();

            PreparedStatement stmtConta = conn.prepareStatement(sqlConta, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtPoupanca = conn.prepareStatement(sqlPoupanca);

            stmtConta.setString(1, numeroConta.gerarNumero("POUPANCA"));
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

            System.out.println(idconta);

            stmtPoupanca.setDouble(1, conta.getTaxaDeRendimento());
            stmtPoupanca.setInt(2, idconta);

            stmtPoupanca.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ContaPoupanca getContaPoupanca(ResultSet rs, Cliente cliente) {
        try (Connection conn = Conexao.conexao()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM conta_poupanca WHERE id_conta = ?");
            stmt.setInt(1, rs.getInt("id_conta"));

            ResultSet rsPoupanca = stmt.executeQuery();
            if (rsPoupanca.next()) {
                return new ContaPoupanca(
                        rs.getString("numero_conta"),
                        rs.getString("agencia"),
                        rs.getDouble("saldo"),
                        cliente,
                        rsPoupanca.getDouble("taxa_rendimento")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletarContaPoupanca(Conta conta){

        String sqlpoupanca = "DELETE FROM conta_poupanca WHERE id_conta= ?";
        String sqlConta = "DELETE FROM conta WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmtPoupanca = conn.prepareStatement(sqlpoupanca);
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);

            ContaDAO contaDAO = new ContaDAO();
            int idConta = contaDAO.getIDConta(conta.getNumeroConta());

            stmtPoupanca.setInt(1, idConta);
            stmtPoupanca.executeUpdate();

            stmtConta.setInt(1, idConta);
            stmtConta.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
