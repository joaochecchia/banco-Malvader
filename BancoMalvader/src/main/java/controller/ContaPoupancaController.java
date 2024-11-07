package controller;

import dao.ClienteDAO;
import dao.ContaPoupancaDAO;
import model.Cliente;
import model.ContaPoupanca;
import util.GerarNumeroConta;
import util.GerarAgencia;

public class ContaPoupancaController {
    public void criarContaPoupanca(double saldo, double taxaDeRendimeto, String nomeCliente){
        GerarAgencia gerarAgencia = new GerarAgencia();

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente(nomeCliente);

        String agencia = gerarAgencia.gerarAgencia(cliente.getEndereco().getEstado());

        GerarNumeroConta gerarNumeroConta = new GerarNumeroConta();
        String numeroConta = gerarNumeroConta.gerarNumero("POUPANCA");

        ContaPoupanca contaPoupanca = new ContaPoupanca(9999999, agencia, saldo, cliente, taxaDeRendimeto);

        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
        contaPoupancaDAO.registrarContaPoupanca(contaPoupanca, cliente);
    }
}
