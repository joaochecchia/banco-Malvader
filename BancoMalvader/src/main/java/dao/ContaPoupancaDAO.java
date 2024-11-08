package dao;

import model.Cliente;
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

            stmtConta.setString(1, "dasd");
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
}
