package dao;

import model.Endereco;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO {
    //a chave estrangeira vem da classe, pois ela ja foi gerada ao criar usuario e funcionario.

    public void registrarEnderecoDAO(Endereco endereco){
        //conecta no banco de dados
        Conexao conectar = new Conexao();

        //comando sql
        String comandoSql = "INSERT INTO endereco(cep, numero_casa, bairro, cidade, estado, id_usuario) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        //tratamento de erros
        try(Connection conn = Conexao.conexao()){
            //prepara o comando sql
            PreparedStatement stmt = conn.prepareStatement(comandoSql);

            //substitui as interrogacoes
            stmt.setString(1, endereco.getCep());
            stmt.setInt(2, endereco.getNumeroCasa());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getEstado());
            stmt.setInt(6, endereco.getIdUsuario());

            //executa o update
            stmt.executeUpdate();

        } catch(SQLException e){
            //imprime o erro
            System.out.println(e.getMessage());
        }
    }

    public Endereco criarClasse(int idUsuario){

        String sql = "SELECT * FROM endereco WHERE id_usuario = ?";

        try(Connection conn = Conexao.conexao()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Endereco endereco = new Endereco(rs.getInt(1), rs.getString(2)
                        , rs.getInt(3), rs.getString(4), rs.getString(5)
                        , rs.getString(6), rs.getInt(7) );

                return endereco;
            } else{
                return null;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
