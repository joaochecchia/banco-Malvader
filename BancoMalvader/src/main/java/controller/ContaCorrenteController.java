package controller;

import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import model.Cliente;
import model.ContaCorrente;
import util.GerarAgencia;
import util.GerarNumeroConta;

import java.time.LocalDate;

public class ContaCorrenteController {
    public void contaCorrenteController(double saldo, double limite
            , LocalDate dataDeVencimento, String nomeCliente){
        GerarAgencia gerarAgencia = new GerarAgencia();
        GerarNumeroConta gerarNumeroConta = new GerarNumeroConta();

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente(nomeCliente);

        String agencia = gerarAgencia.gerarAgencia(cliente.getEndereco().getEstado());
        String numeroConta = gerarNumeroConta.gerarNumero("CORRENTE");

        ContaCorrente contaCorrenteNova = new ContaCorrente("1asd", agencia, saldo, cliente, limite, dataDeVencimento);

        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        contaCorrenteDAO.registrarContaCorrente(contaCorrenteNova, cliente);
    }
}
