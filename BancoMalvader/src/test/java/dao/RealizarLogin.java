package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Conexao;

public class RealizarLogin {
    public static boolean realizarLogin(String nome, String senha) {
        //realiza conexao com o banco de dados
        Conexao conexao = new Conexao();

        //comando sql para checar o usuario e senha
        String comando_sql = "SELECT * FROM usuario WHERE senha = ? AND nome = ?";

        try (Connection conn = Conexao.conexao();) {
            //usa a classe PreparedStatement para setar as strings senha e nome
            PreparedStatement stmt = conn.prepareStatement(comando_sql);

            stmt.setString(1, senha);
            stmt.setString(2, nome);

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
}
