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
        //strings genericas de inserção
        String sqlConta = "INSERT INTO conta(numero_conta, agencia, saldo, tipo_conta, id_cliente)" +
                "values (?, ?, ?, ?, ?)";

        String sqlPoupanca = "INSERT INTO conta_poupanca(taxa_rendimento, id_conta)" +
                "values(?, ?)";

        try(Connection conn = Conexao.conexao()){
            //Classe util para genrar um numero da conta
            GerarNumeroConta numeroConta = new GerarNumeroConta();
            GerarAgencia numeroAgencia = new GerarAgencia();
            //preparar a string
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtPoupanca = conn.prepareStatement(sqlPoupanca);

            stmtConta.setString(1, numeroConta.gerarNumero("POUPANCA"));//gera um numero para conta poupanca
            stmtConta.setString(2, numeroAgencia.gerarAgencia(cliente.getEndereco().getEstado()));//gera uma agencia para o estado
            stmtConta.setDouble(3, conta.getSaldo());
            stmtConta.setString(4, "POUPANCA");
            stmtConta.setInt(5, cliente.getIdCliente());

            stmtConta.executeUpdate();
            ResultSet rs = stmtConta.getGeneratedKeys();

            //pega o id da conta para inserir a poupancaça
            int idconta = 0;
            if (rs.next()) {
                idconta = rs.getInt(1);
            }
            rs.close();

            stmtPoupanca.setDouble(1, conta.getTaxaDeRendimento());
            stmtPoupanca.setInt(2, idconta);
            //executa a inserção
            stmtPoupanca.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ContaPoupanca getContaPoupanca(ResultSet rs, Cliente cliente) {//recebe o resultSet da classe contaDAO e a calsse cliente
        try (Connection conn = Conexao.conexao()) {
            //preparar a string
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM conta_poupanca WHERE id_conta = ?");
            stmt.setInt(1, rs.getInt("id_conta"));

            //executa a busca
            ResultSet rsPoupanca = stmt.executeQuery();
            if (rsPoupanca.next()) {
                //retorna conta poupança
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

    public void editarParaContaPoupanca(int idConta, double taxa){
        //codigo para edição de conta, caso um funcionario mude uma conta corrente para poupançã
        String sql = "INSERT INTO conta_poupanca(taxa_rendimento, id_conta) VALUES (?, ?);";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, taxa);
            stmt.setInt(2, idConta);

            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarContaPoupanca(int idConta){
        //string sql generica
        String sqlpoupanca = "DELETE FROM conta_poupanca WHERE id_conta= ?";
        String sqlConta = "DELETE FROM conta WHERE id_conta = ?";

        try(Connection conn = Conexao.conexao()){
            //preparar as  strings
            PreparedStatement stmtPoupanca = conn.prepareStatement(sqlpoupanca);
            PreparedStatement stmtConta = conn.prepareStatement(sqlConta);

            stmtPoupanca.setInt(1, idConta);
            stmtPoupanca.executeUpdate();

            //executar a deleção
            stmtConta.setInt(1, idConta);
            stmtConta.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
