package controller;

import dao.EnderecoDAO;
import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import model.Endereco;
import model.Funcionario;

import java.time.LocalDate;

public class FuncionarioController {
    public void criarFuncionario(String nome, String cpf, LocalDate dataNascimento
            , String telefone, String codigoFuncionario, String cargo, String senha,
            String cep, int numero, String bairro, String cidade, String estado) {

        Endereco endereco = new Endereco(0, cep, numero, bairro, cidade, estado, 0);

        Funcionario funcionarioNovo = new Funcionario(0, nome,  cpf, dataNascimento, telefone,
                endereco, codigoFuncionario, cargo, senha, "FUNCIONARIO");

        FuncionarioDAO registrarFuncionario = new FuncionarioDAO();
        EnderecoDAO registrarEndereco = new EnderecoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        registrarFuncionario.registrarFuncionarioDAO(funcionarioNovo);
        registrarEndereco.registrarEnderecoDAO(endereco, usuarioDAO.usuarioGetId(nome));
    }
}
