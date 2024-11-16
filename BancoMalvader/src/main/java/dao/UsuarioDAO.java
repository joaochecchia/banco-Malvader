package dao;

import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public boolean hasUsuario(String nome){
        String sql = "SELECT nome FROM usuario WHERE nome = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public int usuarioGetId(String nome){
        String sql = "SELECT id_usuario FROM usuario WHERE nome = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            rs.next();


            return rs.getInt(1);

        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
