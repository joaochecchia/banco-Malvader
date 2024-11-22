package controller;

import dao.ClienteDAO;
import dao.EnderecoDAO;
import dao.UsuarioDAO;
import model.Cliente;
import model.Endereco;
import java.time.LocalDate;

public class ClienteController {
    //classe que cria o cliente fornecido pelo view e nabda a classe par clienteDAO
    public void criarCliente(String nome, String cpf, LocalDate dataNascimento
            , String telefone, String senha, String cep, String local,int numero
            , String bairro, String cidade, String estado){

        Endereco endereco = new Endereco(0, cep, local,numero, bairro
                , cidade, estado, 0);

        Cliente clienteNovo = new Cliente(0, nome, cpf, dataNascimento
                , telefone, endereco, "CLIENTE",senha, 0);

        ClienteDAO clienteDAO = new ClienteDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        clienteDAO.criarCliente(clienteNovo);
        enderecoDAO.registrarEnderecoDAO(endereco, usuarioDAO.usuarioGetId(nome));
    }

    //recebe a classe do view, pega o id e manda para o ClienteDAO
    public void editarClienteController(Cliente cliente){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int idUsuario = usuarioDAO.usuarioGetId(cliente.getNome());

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.editarCliente(cliente, idUsuario);
    }
}
