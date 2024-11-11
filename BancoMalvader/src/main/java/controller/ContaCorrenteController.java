package controller;

import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import dao.ContaDAO;
import dao.UsuarioDAO;
import model.Cliente;
import model.ContaCorrente;
import model.Conta;
import util.GerarAgencia;
import util.GerarNumeroConta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

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

    public void editarContaController(Conta conta, String tipo){
        ContaDAO contaDAO = new ContaDAO();

        if(conta instanceof ContaCorrente){
            ContaCorrente contaCorrente = (ContaCorrente) conta;

            contaDAO.editarConta(tipo, contaCorrente.getLimite(), contaCorrente.getDataVencimento(), contaCorrente.getNumeroConta());
        } else{
            LocalDate vazio = LocalDate.parse("0000-00-00");
            contaDAO.editarConta(tipo, 0, vazio, conta.getNumeroConta());
        }
    }

    public static void main(String[] args) {
        ClienteDAO clienteDao = new ClienteDAO();
        Cliente cliente = clienteDao.getClasseCliente("hugo12");

        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> a = contaDAO.getClasConta(cliente);

        System.out.println(a.size());

        Conta b = a.get(0);
        ContaCorrente conta = (ContaCorrente) b;

        conta.setNumeroConta("NumeroLegal");

        System.out.println(conta.getLimite());
        System.out.println(conta.getDataVencimento().toString());
        System.out.println(conta.getNumeroConta());

        contaDAO.editarConta("CORRENTE", conta.getLimite(), conta.getDataVencimento(), conta.getNumeroConta());
    }
}
