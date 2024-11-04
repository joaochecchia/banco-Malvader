package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Endereco;
import model.Funcionario;
import util.Conexao;

public class RealizarLoginDAO {
    public static boolean realizarLogin(String nome, String senha, boolean empregado) {
        //realiza conexao com o banco de dados
        Conexao conexao = new Conexao();

        //comando sql para checar o usuario , senha e tipo na tabela
        String comando_sql = "SELECT * FROM usuario WHERE senha = ? AND nome = ? AND tipo_usuario = ?";

        try (Connection conn = Conexao.conexao()) {
            //usa a classe PreparedStatement para setar as strings senha e nome
            PreparedStatement stmt = conn.prepareStatement(comando_sql);

            stmt.setString(1, senha);
            stmt.setString(2, nome);
            stmt.setString(3, !empregado ? "EMPREGADO" : "FUNCIONARIO");

            //resultado da busca
            ResultSet resultado = stmt.executeQuery();

            //se a busca tiver um 1 intem(.next = true) significa que senha e usuario sao compativeis
            if(resultado.next()){
                System.out.println("Login feito com sucesso.");

                return true;
            } else{
                // caso contrário, não
                return false;
            }

        } catch (SQLException e) {
            //mesagem de erro ao se conectar com o banco de dados
            System.out.println("Erro ao realizar login: " + e.getMessage());

            return false;
        }
    }

    public static Funcionario getFuncionario(String nome){
        Conexao conexao = new Conexao();

        String sqlUsuario = "SELECT * FROM usuario WHERE nome = ?";
        String sqlFuncionario = "SELECT * FROM funcionario WHERE id_usuario = ?";
        String sqlEndereco = "SELECT * FROM endereco WHERE id_usuario = ?";

        try (Connection conn = Conexao.conexao()){
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario);
            PreparedStatement stmtCliente = conn.prepareStatement(sqlFuncionario);
            PreparedStatement stmtEndereco = conn.prepareStatement(sqlFuncionario);

            stmtUsuario.setString(1, nome);

            ResultSet rs = stmtUsuario.executeQuery();

            if(rs.next()) {
                int id = rs.getInt(1);
                String cpf = rs.getString(2);
                String Nascimento = rs.getString(3);
                String telefone = rs.getString(4);
                String tipo = rs.getString(5);
                String senha = rs.getString(6);

                //Endereco endereco = new Endereco();

                //Funcionario funcionario = new Funcionario(id, nome, cpf, , telefone, , );
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
