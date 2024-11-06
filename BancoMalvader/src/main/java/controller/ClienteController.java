package controller;

import dao.ClienteDAO;
import model.Cliente;
import model.Endereco;
import java.time.LocalDate;

public class ClienteController {
    public void criarCliente(String nome, String cpf, LocalDate dataNascimento
    , String telefone,Endereco endereco, String senha){

        Cliente clienteNovo = new Cliente(0, nome, cpf, dataNascimento
                , telefone, endereco, "CLIENTE",senha);

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.criarCliente(clienteNovo);
    }
}
