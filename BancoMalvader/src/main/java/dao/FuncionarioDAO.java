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
        //sql generiaca
        String comandoSqlUsuario = "INSERT INTO usuario(nome, cpf, data_nascimento, telefone, tipo_usuario, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        String comandoSqlFuncionario = "INSERT INTO funcionario(codigo_funcionario, cargo, id_usuario) " +
                "VALUES(?, ?, ?)";

        try (Connection conn = Conexao.conexao()) {
            //preparar a string para a inserção
            PreparedStatement stmtUsuario = conn.prepareStatement(comandoSqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);//pega as chaves geradas
            PreparedStatement stmtFuncionario = conn.prepareStatement(comandoSqlFuncionario);

            stmtUsuario.setString(1, funcionario.getNome());
            stmtUsuario.setString(2, funcionario.getCpf());
            stmtUsuario.setString(3, funcionario.getDataDeNascimento().toString());
            stmtUsuario.setString(4, funcionario.getTelefone());
            stmtUsuario.setString(5, funcionario.getTipo());
            stmtUsuario.setString(6, funcionario.getSenha());

            //executa o update
            stmtUsuario.executeUpdate();
            ResultSet rs = stmtUsuario.getGeneratedKeys();

            int idUsuario = 0;
            if (rs.next()) {
                //pega a classe gerarda
                idUsuario = rs.getInt(1);
            }
            rs.close();

            //prepara a string funcionario
            stmtFuncionario.setString(1, funcionario.getCodigoFuncionario());
            stmtFuncionario.setString(2, funcionario.getCargo());
            stmtFuncionario.setInt(3, idUsuario);

            //executa a insteção na tabela funcionario
            stmtFuncionario.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Funcionario getClassFuncionario(String nome) {
        //String de consulta na tabela funcionario
        String sqlUsuario = "SELECT * FROM usuario WHERE nome = ?";
        String sqlFuncionario = "SELECT * FROM funcionario WHERE id_usuario = ?";

        try (Connection conn = Conexao.conexao()) {
            //prepara a string
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario);
            stmtUsuario.setString(1, nome);
            ResultSet rsUsuario = stmtUsuario.executeQuery();

            if (rsUsuario.next()) {
                //se tiver correspondencia monte a classe funcionario
                int id = rsUsuario.getInt(1);
                String cpf = rsUsuario.getString(3);
                String nascimento = rsUsuario.getString(4);
                String telefone = rsUsuario.getString(5);
                String tipo = rsUsuario.getString(6);
                String senha = rsUsuario.getString(7);

                PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario);
                stmtFuncionario.setInt(1, id);
                ResultSet rsFuncionario = stmtFuncionario.executeQuery();

                if (rsFuncionario.next()) {
                    String codigoFuncionario = rsFuncionario.getString(2);
                    String cargo = rsFuncionario.getString(3);

                    EnderecoDAO enderecoDAO = new EnderecoDAO();
                    Endereco endereco = enderecoDAO.getClassEndereco(id);

                    LocalDate dataNascimento= LocalDate.parse(nascimento);

                    return new Funcionario(id, nome, cpf, dataNascimento, telefone, endereco,
                            codigoFuncionario, cargo, senha, tipo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
