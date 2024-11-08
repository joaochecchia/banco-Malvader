package controller;

import dao.ClienteDAO;
import dao.EnderecoDAO;
import dao.UsuarioDAO;
import model.Cliente;
import model.Endereco;
import java.time.LocalDate;

public class ClienteController {
    public void criarCliente(String nome, String cpf, LocalDate dataNascimento
            , String telefone, String senha, String cep, int numero
            , String bairro, String cidade, String estado){

        Endereco endereco = new Endereco(0, cep, numero, bairro
                , cidade, estado, 0);

        Cliente clienteNovo = new Cliente(0, nome, cpf, dataNascimento
                , telefone, endereco, "CLIENTE",senha, 0);

        ClienteDAO clienteDAO = new ClienteDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        clienteDAO.criarCliente(clienteNovo);
        enderecoDAO.registrarEnderecoDAO(endereco, usuarioDAO.usuarioGetId(nome));
    }
}
