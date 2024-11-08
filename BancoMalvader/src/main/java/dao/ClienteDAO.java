package dao;

import model.Cliente;
import model.Endereco;
import util.Conexao;

import java.sql.*;
import java.time.LocalDate;

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

    public Cliente getClasseCliente(String nomeCliente){
        String sqlUsuario = "SELECT * FROM usuario WHERE nome = ?";
        String sqlCliente = "SELECT * FROM cliente WHERE id_usuario = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, PreparedStatement.RETURN_GENERATED_KEYS);

            stmtUsuario.setString(1, nomeCliente);
            ResultSet rsUsuario = stmtUsuario.executeQuery();

            if(rsUsuario.next()){
                int id = rsUsuario.getInt(1);
                String cpf = rsUsuario.getString(3);
                String nascimento = rsUsuario.getString(4);
                String telefone = rsUsuario.getString(5);
                String tipo = rsUsuario.getString(6);
                String senha = rsUsuario.getString(7);

                stmtCliente.setInt(1, id);

                int idCliente = 0;
                ResultSet rs = stmtCliente.executeQuery();

                if(rs.next()){
                    idCliente = rs.getInt(1);
                }

                EnderecoDAO enderecoDAO = new EnderecoDAO();
                Endereco enderecoCliente = enderecoDAO.getClassEndereco(id);

                LocalDate dataNascimento= LocalDate.parse(nascimento);

                Cliente cliente = new Cliente(id, nomeCliente, cpf, dataNascimento, telefone
                        , enderecoCliente, tipo, senha, idCliente);

                return cliente;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Boolean verificarCliente(String cpf){
        String sql = "SELECT * FROM usuario WHERE nome = ?";

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
