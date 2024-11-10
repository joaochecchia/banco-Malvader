package dao;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Cliente;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContaDAO {
    public ArrayList<Conta> getClasConta(Cliente cliente) {
        String sql = "SELECT * FROM conta WHERE id_cliente = ?";

        try (Connection conn = Conexao.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());

            ResultSet rs = stmt.executeQuery();

            ArrayList<Conta> contas = new ArrayList<>();

            while (rs.next()) {
                if ("CORRENTE".equals(rs.getString("tipo_conta"))) {
                    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
                    contas.add(contaCorrenteDAO.getContaCorrente(rs, cliente));
                } else if ("POUPANCA".equals(rs.getString("tipo_conta"))) {
                    ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
                    contas.add(contaPoupancaDAO.getContaPoupanca(rs, cliente));
                }
            }

            return contas;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editarConta(String tipoConta, double limite, LocalDate vencimento, String numeroConta){
        String sqlConta = "UPDATE conta SET tipo_conta = ? WHERE id_conta = ?";
        String sqlCorrente = "UPDATE conta_corrente SET limite = ?, data_vencimento = ?" +
                "WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);
            PreparedStatement stmtCorrente = conn.prepareStatement(sqlCorrente);

            ContaDAO contaDAO = new ContaDAO();
            int idConta = contaDAO.getIDConta(numeroConta);

            stmtConta.setString(1, tipoConta);
            stmtConta.setInt(2, idConta);

            stmtCorrente.setDouble(1, limite);
            stmtCorrente.setString(2, vencimento.toString());
            stmtCorrente.setString(3, numeroConta);

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getIDConta(String numeroConta){
        String sql = "SELECT id_conta FROM conta WHERE numero_conta = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroConta);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getInt(1);
            } else{
                return 0;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente c = clienteDAO.getClasseCliente("hugo12");

        System.out.println("adsada" + c.getIdCliente());
        System.out.println(contaDAO.getClasConta(c).size());
    }
}


