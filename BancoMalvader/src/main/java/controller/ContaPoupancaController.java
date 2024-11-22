package controller;

import dao.ClienteDAO;
import dao.ContaDAO;
import dao.ContaPoupancaDAO;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import util.GerarNumeroConta;
import util.GerarAgencia;

public class ContaPoupancaController {
    //cria a classe conta poupança e passa para a DAO
    public void criarContaPoupanca(double saldo, double taxaDeRendimeto, String nomeCliente){
        GerarAgencia gerarAgencia = new GerarAgencia();

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente(nomeCliente);

        String agencia = gerarAgencia.gerarAgencia(cliente.getEndereco().getEstado());

        GerarNumeroConta gerarNumeroConta = new GerarNumeroConta();
        String numeroConta = gerarNumeroConta.gerarNumero("POUPANCA");

        ContaPoupanca contaPoupanca = new ContaPoupanca(numeroConta, agencia, saldo, cliente, taxaDeRendimeto);

        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
        contaPoupancaDAO.registrarContaPoupanca(contaPoupanca, cliente);
    }


}
