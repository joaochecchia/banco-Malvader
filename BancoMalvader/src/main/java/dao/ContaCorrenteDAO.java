package dao;

import model.Cliente;
import model.ContaCorrente;
import model.Conta;
import model.Endereco;
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

            stmtConta.setString(1, "a");
            stmtConta.setString(2, numeroAgencia.gerarAgencia(cliente.getEndereco().getEstado()));
            stmtConta.setDouble(3, conta.getSaldo());
            stmtConta.setString(4, "CORRENTE");
            stmtConta.setInt(5, 9);

            stmtConta.executeUpdate();
            ResultSet rs = stmtConta.getGeneratedKeys();

            int idconta = 0;
            if (rs.next()) {
                idconta = rs.getInt(1);
            }
            rs.close();

            System.out.println("get id: " + cliente.getIdCliente());

            stmtCorrente.setDouble(1, conta.getLimite());
            stmtCorrente.setString(2, conta.getDataVencimento().toString());
            stmtCorrente.setInt(3, cliente.getIdCliente());

            stmtCorrente.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LocalDate l = LocalDate.parse("2004-05-09");

        ClienteDAO i = new ClienteDAO();
        Cliente en = i.getClasseCliente("lukas");

        ContaCorrente c = new ContaCorrente("n", "a", 123, en, 200, l);

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente("lukas");

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa: " + cliente.getIdCliente());

        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        contaCorrenteDAO.registrarContaCorrente(c, en);
    }
}
