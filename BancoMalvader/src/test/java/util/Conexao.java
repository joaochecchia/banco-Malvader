package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection conexao() {
        String url = "jdbc:mysql://localhost/banco_malvader";
        String usuario = "root";
        String senha = "5desetembro";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão bem-sucedida!");
            return conexao;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do banco de dados não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Conexao c = new Conexao();

        Conexao.conexao();
    }
}
