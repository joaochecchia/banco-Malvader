package dao;
import model.Funcionario;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegistrarFuncionarioDAO {
    // mesmo funcionamento do endereco, porem preenche usuario e funcionario ao mesmo tempo
    //por usuario nao poder ser instanciado.

    //diferente do endereco preferi puxar o id quando se coloca a classe na tabela

    public void registrarFuncionarioDAO(Funcionario funcionario){
        Conexao conectar = new Conexao();

        String comandoSqlUsuario = "INSERT INTO usuario(nome, cpf, data_nascimento, telefone, tipo_usuario, senha)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        String comandoSqlFuncionario = "INSERT INTO funcionario(codigo_funcionario, cargo, id_usuario)" +
                "VALUES(?, ?, ?)";

        try(Connection conn = Conexao.conexao()){
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

        } catch(SQLException e){

            System.out.println( e.getMessage());

        }
    }

    public static void main(String[] args) {
        RegistrarFuncionarioDAO a = new RegistrarFuncionarioDAO();

        Funcionario b = new Funcionario(4,"asdeasdad", "709153", LocalDate.of(2004, 9, 22), "123123-1231231", "Luzania", "131231123", "aaaaaaa", "senha", "FUNCIONARIO");

        a.registrarFuncionarioDAO(b);
    }
}
