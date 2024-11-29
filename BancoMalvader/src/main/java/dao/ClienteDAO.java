package dao;

import model.Cliente;
import model.Endereco;
import util.Conexao;

import java.sql.*;
import java.time.LocalDate;

public class ClienteDAO {
    public void criarCliente(Cliente cliente){
        //String generica para criar cliente
        String sqlUsuario = "INSERT INTO usuario(nome, cpf, data_nascimento, telefone, tipo_usuario, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        String sqlCliente = "INSERT INTO cliente(id_usuario) values (?)";

        try(Connection conn = Conexao.conexao()){
            //preparação da string
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente);

            stmtUsuario.setString(1, cliente.getNome());
            stmtUsuario.setString(2, cliente.getCpf());
            stmtUsuario.setString(3, cliente.getDataDeNascimento().toString());
            stmtUsuario.setString(4, cliente.getTelefone());
            stmtUsuario.setString(5, "CLIENTE");//tipo fixo "CLIENTE"
            stmtUsuario.setString(6, cliente.getSenha());

            stmtUsuario.executeUpdate();
            ResultSet rs = stmtUsuario.getGeneratedKeys();

            //pega o id usuario para inserir na tabela cliente
            int idUsuario = 0;
            if(rs.next()){
                idUsuario = rs.getInt(1);
            }
            rs.close();

            //prepara a tabela cliente
            stmtCliente.setInt(1, idUsuario);

            //executa o update
            stmtCliente.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void editarCliente(Cliente cliente, int idUsuario){
        //String sql generica
        String sqlUsuario = "UPDATE usuario SET nome = ?, cpf = ?, data_nascimento = ?," +
                "telefone = ?, senha = ? WHERE id_usuario = ?";
        String sqlEndereco = "UPDATE endereco SET cep = ?, local = ?, numero_casa = ?, " +
                "bairro = ?, cidade = ?, estado = ? WHERE id_usuario = ?";

        try(Connection conn = Conexao.conexao()) {
            //prepara as strings sql
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario);
            PreparedStatement stmtEndereco = conn.prepareStatement(sqlEndereco);

            stmtUsuario.setString(1, cliente.getNome());
            stmtUsuario.setString(2, cliente.getCpf());
            stmtUsuario.setString(3, cliente.getDataDeNascimento().toString());
            stmtUsuario.setString(4, cliente.getTelefone());
            stmtUsuario.setString(5, cliente.getSenha());
            stmtUsuario.setInt(6, idUsuario);

            //pega a classe endereco para registrar na tabela
            Endereco endereco = cliente.getEndereco();

            stmtEndereco.setString(1, endereco.getCep());
            stmtEndereco.setString(2, endereco.getLocal());
            stmtEndereco.setInt(3, endereco.getNumeroCasa());
            stmtEndereco.setString(4, endereco.getBairro());
            stmtEndereco.setString(5, endereco.getCidade());
            stmtEndereco.setString(6, endereco.getEstado());
            stmtEndereco.setInt(7, idUsuario);

            //executa a inserção
            stmtUsuario.executeUpdate();
            stmtEndereco.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Cliente getClasseCliente(String nomeCliente){
        //String sql generica
        String sqlUsuario = "SELECT * FROM usuario WHERE nome = ?";
        String sqlCliente = "SELECT * FROM cliente WHERE id_usuario = ?";

        try(Connection conn = Conexao.conexao()){
            //prepara a classe
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, PreparedStatement.RETURN_GENERATED_KEYS);

            stmtUsuario.setString(1, nomeCliente);
            ResultSet rsUsuario = stmtUsuario.executeQuery();

            //se tiver um ResultSet na pesquisa
            if(rsUsuario.next()){
                //monte a classe
                int id = rsUsuario.getInt(1);
                String cpf = rsUsuario.getString(3);
                String nascimento = rsUsuario.getString(4);
                String telefone = rsUsuario.getString(5);
                String tipo = rsUsuario.getString(6);
                String senha = rsUsuario.getString(7);

                stmtCliente.setInt(1, id);

                //pega na tabela cliente com o id pego
                int idCliente = 0;
                ResultSet rs = stmtCliente.executeQuery();

                if(rs.next()){
                    idCliente = rs.getInt(1);
                }

                //cria a classe endereço relacionada ao id
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                Endereco enderecoCliente = enderecoDAO.getClassEndereco(id);

                LocalDate dataNascimento= LocalDate.parse(nascimento);

                Cliente cliente = new Cliente(id, nomeCliente, cpf, dataNascimento, telefone
                        , enderecoCliente, tipo, senha, idCliente);
                //retorna o id
                return cliente;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Boolean verificarCliente(String cpf){
        //String sql generica
        String sql = "SELECT * FROM usuario WHERE nome = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql); //prepara a strinh

            stmt.setString(1, cpf);
            //se tiver um resultSet.next() significa que o cliente existe
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
