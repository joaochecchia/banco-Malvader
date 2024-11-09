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
import java.util.ArrayList;

public class ContaDAO {
    public ArrayList<Conta> getClasConta(Cliente cliente) {
        String sql = "SELECT * FROM conta WHERE id_cliente = ?";

        try (Connection conn = Conexao.conexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());

            ResultSet rs = stmt.executeQuery();
            Conta conta = null;

            ArrayList<Conta> contas = new ArrayList<>();
            

            while (rs.next()) {
                System.out.println("agencia" + rs.getString("agencia"));

                if ("CORRENTE".equals(rs.getString("tipo_conta"))) {
                    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
                    contas.add(contaCorrenteDAO.getContaCorrente(rs, cliente));
                } else {
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


