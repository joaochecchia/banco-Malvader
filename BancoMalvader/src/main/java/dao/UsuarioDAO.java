package dao;

import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public boolean hasUsuario(String nome){
        //String sql generica
        String sql = "SELECT nome FROM usuario WHERE nome = ?";

        try(Connection conn = Conexao.conexao()){
            //preparar a string para a consulta
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);
            //recebe o id
            ResultSet rs = stmt.executeQuery();
            //se tiver 1 coluna, significa que o usuario existe
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public int usuarioGetId(String nome){
        //String sql generica
        String sql = "SELECT id_usuario FROM usuario WHERE nome = ?";

        try(Connection conn = Conexao.conexao()){
            //prepara a string generica
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            //retorna o idUsuario
            return rs.getInt(1);

        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
