package controller;

import dao.ContaPoupancaDAO;
import model.Cliente;
import model.ContaPoupanca;
import util.GerarNumeroConta;
import util.GerarAgencia;

public class ContaPoupancaController {
    public void criarContaPoupanca(double saldo, Cliente cliente, double taxaDeRendimeto){
        GerarAgencia gerarAgencia = new GerarAgencia();
        String agencia = gerarAgencia.gerarAgencia(cliente.getEndereco().getEstado());

        GerarNumeroConta gerarNumeroConta = new GerarNumeroConta();
        String numeroConta = gerarNumeroConta.gerarNumero("POUPANCA");

        ContaPoupanca contaPoupanca = new ContaPoupanca(0, agencia, saldo, cliente, taxaDeRendimeto);

        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
        contaPoupancaDAO.registrarContaPoupanca(contaPoupanca, cliente);
    }
}
