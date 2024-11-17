package controller;

import dao.*;
import model.Cliente;
import model.ContaCorrente;
import model.Conta;
import model.ContaPoupanca;
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

    public void editarContaController(Conta conta, String tipo, String numeroContaOriginal){
        ContaDAO contaDAO = new ContaDAO();

        if(conta instanceof ContaCorrente){
            ContaCorrente contaCorrente = (ContaCorrente) conta;

            contaDAO.editarConta(tipo, contaCorrente.getLimite(), contaCorrente.getDataVencimento(), contaCorrente.getNumeroConta(), numeroContaOriginal);
        } else{
            LocalDate vazio = LocalDate.parse("1001-01-01");
            contaDAO.editarConta(tipo, 0, vazio, conta.getNumeroConta(), numeroContaOriginal);
        }
    }

    public void mudarTipoDeContaController(String tipoNovo, String numero, double taxa, double limite, LocalDate dataVencimento){
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(numero);

        if(tipoNovo.equalsIgnoreCase("CORRENTE")){
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            contaCorrenteDAO.editarContaCorrente(idConta, limite, dataVencimento);

            contaDAO.deletarConta(idConta, "conta_poupanca");
        } else{
            ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
            contaPoupancaDAO.editarContaPoupanca(idConta, taxa);

            contaDAO.deletarConta(idConta, "conta_corrente");
        }
    }

    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente("hugo12");
        ArrayList<Conta> contas = contaDAO.getClasConta(cliente);
        Conta conta = contas.get(2);

        System.out.println("numero conta: " + conta.getNumeroConta());

        ContaCorrenteController controller = new ContaCorrenteController();
        conta.setNumeroConta("numero legal");
        controller.editarContaController(conta, "CORRENTE", "numeroLEGAL");

    }
}
