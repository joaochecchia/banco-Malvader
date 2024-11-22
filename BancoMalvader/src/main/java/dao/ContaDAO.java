package dao;

import model.Conta;
import model.Cliente;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContaDAO {
    public ArrayList<Conta> getClasConta(Cliente cliente) {
        //String sql generica
        String sql = "SELECT * FROM conta WHERE id_cliente = ?";

        //faz a conexão
        try (Connection conn = Conexao.conexao()) {
            //prepara a string
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());

            //result set com a seleção
            ResultSet rs = stmt.executeQuery();
            //arrayList para armazenar as contas
            ArrayList<Conta> contas = new ArrayList<>();

            //enquanto  tiver contas correspondentes na tabela
            while (rs.next()) {
                //se o tipo for corrente puxe o DAO para pegar a conta corrente
                if ("CORRENTE".equals(rs.getString("tipo_conta"))) {
                    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
                    contas.add(contaCorrenteDAO.getContaCorrente(rs, cliente));
                } else if ("POUPANCA".equals(rs.getString("tipo_conta"))) {
                    //se o tipo for poupanca puxe o DAO para pegar a conta poupanca
                    ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
                    contas.add(contaPoupancaDAO.getContaPoupanca(rs, cliente));
                }
            }
            //retorne o array das contas
            return contas;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void alterarConta(String tipoConta, double limite, LocalDate vencimento, String numeroConta, String numeroContaOriginal, int idConta){
        //string sql generica
        String sqlConta = "UPDATE conta SET tipo_conta = ?, numero_conta = ? WHERE id_conta = ?";
        String sqlCorrente = "UPDATE conta_corrente SET limite = ?, data_vencimento = ? WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            //prepara a string sql
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);
            PreparedStatement stmtCorrente = conn.prepareStatement(sqlCorrente);

            stmtConta.setString(1, tipoConta);
            stmtConta.setString(2, numeroConta);
            stmtConta.setInt(3, idConta);

            //Executa a edição
            stmtConta.executeUpdate();

            if(tipoConta.equals("Corrente")){
                //se o tipo da conta for corrente, edita seus atributos
                stmtCorrente.setDouble(1, limite);
                stmtCorrente.setString(2, vencimento.toString());
                stmtCorrente.setInt(3, idConta);

                stmtCorrente.executeUpdate();
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarConta(int idConta, String tipo_conta){
        //String generica de deletar conta
        String sql = "DELETE FROM " + tipo_conta + " WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            //preparaçãO DA string
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idConta);

            //executa a deleção
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getIDConta(String numeroConta){
        //String sql generica
        String sql = "SELECT id_conta FROM conta WHERE numero_conta = ?";

        try(Connection conn = Conexao.conexao()){//Conexão
            //prepara a string sql
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroConta);

            //resultSet da consulta
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                //se tiver correspondente, retorne o id
                return rs.getInt(1);
            } else{
                return 0;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}


