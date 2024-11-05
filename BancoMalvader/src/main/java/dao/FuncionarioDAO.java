package dao;

import model.Funcionario;
import model.Endereco;
import util.Conexao;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {

    public void registrarFuncionarioDAO(Funcionario funcionario) {
        String comandoSqlUsuario = "INSERT INTO usuario(nome, cpf, data_nascimento, telefone, tipo_usuario, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        String comandoSqlFuncionario = "INSERT INTO funcionario(codigo_funcionario, cargo, id_usuario) " +
                "VALUES(?, ?, ?)";

        try (Connection conn = Conexao.conexao()) {
            PreparedStatement stmtUsuario = conn.prepareStatement(comandoSqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement stmtFuncionario = conn.prepareStatement(comandoSqlFuncionario);

            stmtUsuario.setString(1, funcionario.getNome());
            stmtUsuario.setString(2, funcionario.getCpf());
            stmtUsuario.setString(3, funcionario.getDataDeNascimento().toString());
            stmtUsuario.setString(4, funcionario.getTelefone());
            stmtUsuario.setString(5, funcionario.getTipo());
            stmtUsuario.setString(6, funcionario.getSenha());

            stmtUsuario.executeUpdate();
            ResultSet rs = stmtUsuario.getGeneratedKeys();

            int idUsuario = 0;
            if (rs.next()) {
                idUsuario = rs.getInt(1);
            }
            rs.close();

            stmtFuncionario.setString(1, funcionario.getCodigoFuncionario());
            stmtFuncionario.setString(2, funcionario.getCargo());
            stmtFuncionario.setInt(3, idUsuario);

            stmtFuncionario.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Funcionario getFuncionario(String nome) {
        String sqlUsuario = "SELECT * FROM usuario WHERE nome = ?";
        String sqlFuncionario = "SELECT * FROM funcionario WHERE id_usuario = ?";

        try (Connection conn = Conexao.conexao()) {
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario);
            stmtUsuario.setString(1, nome);
            ResultSet rsUsuario = stmtUsuario.executeQuery();

            System.out.println("estou aqui 1");

            if (rsUsuario.next()) {
                System.out.println("estou aqui 2");
                int id = rsUsuario.getInt(1);
                String cpf = rsUsuario.getString(2);
                String nascimento = rsUsuario.getString(3);
                String telefone = rsUsuario.getString(4);
                String tipo = rsUsuario.getString(5);
                String senha = rsUsuario.getString(6);

                System.out.println("ID: " + id);

                PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario);
                stmtFuncionario.setInt(1, id);
                ResultSet rsFuncionario = stmtFuncionario.executeQuery();

                if (rsFuncionario.next()) {
                    System.out.println("estou aqui 3");
                    String codigoFuncionario = rsFuncionario.getString(2);
                    String cargo = rsFuncionario.getString(3);

                    EnderecoDAO enderecoDAO = new EnderecoDAO();
                    Endereco endereco = enderecoDAO.criarClasse(id);

                    LocalDate dataNascimento= LocalDate.parse("2024-11-05");

                    return new Funcionario(id, nome, cpf, dataNascimento, telefone, endereco,
                            codigoFuncionario, cargo, senha, tipo);
                }
            }

        } catch (SQLException e) {
            System.out.println("estou aqui 4");
            e.printStackTrace();
        }

        System.out.println("asdasdadasd");
        return null;
    }

    public static void main(String[] args) {
        FuncionarioDAO a = new FuncionarioDAO();

        Funcionario b = a.getFuncionario("guilherme");

        System.out.println(b.getDataDeNascimento());
        System.out.println(b.getCargo());
        System.out.println(b.getEndereco());

    }
}
