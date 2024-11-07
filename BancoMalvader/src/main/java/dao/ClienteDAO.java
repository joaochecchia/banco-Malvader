package dao;

import model.Cliente;
import util.Conexao;

import java.sql.*;

public class ClienteDAO {
    public void criarCliente(Cliente cliente){

        String sqlUsuario = "INSERT INTO usuario(nome, cpf, data_nascimento, telefone, tipo_usuario, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        String sqlCliente = "INSERT INTO cliente(id_usuario) values (?)";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente);

            stmtUsuario.setString(1, cliente.getNome());
            stmtUsuario.setString(2, cliente.getCpf());
            stmtUsuario.setString(3, cliente.getDataDeNascimento().toString());
            stmtUsuario.setString(4, cliente.getTelefone());
            stmtUsuario.setString(5, "CLIENTE");
            stmtUsuario.setString(6, cliente.getSenha());

            stmtUsuario.executeUpdate();
            ResultSet rs = stmtUsuario.getGeneratedKeys();

            int idUsuario = 0;
            if(rs.next()){
                idUsuario = rs.getInt(1);
            }
            rs.close();

            stmtCliente.setInt(1, idUsuario);

            stmtCliente.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Boolean verificarCliente(String cpf){
        String sql = "SELECT * FROM usuario WHERE cpf = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return  true;
            } else{
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
