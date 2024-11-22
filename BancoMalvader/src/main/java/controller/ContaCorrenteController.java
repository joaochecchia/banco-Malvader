package controller;

import dao.*;
import model.Cliente;
import model.ContaCorrente;
import model.Conta;
import util.GerarAgencia;
import util.GerarNumeroConta;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaCorrenteController {
    public void registrarContaCorrenteController(double saldo, double limite
            , LocalDate dataDeVencimento, String nomeCliente){
        //recebe os dados referente a tabela conta_corrente, gera a agencia  e numero
        GerarAgencia gerarAgencia = new GerarAgencia();
        GerarNumeroConta gerarNumeroConta = new GerarNumeroConta();

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente(nomeCliente);

        String agencia = gerarAgencia.gerarAgencia(cliente.getEndereco().getEstado());
        String numeroConta = gerarNumeroConta.gerarNumero("CORRENTE");

        //cria uma conta corrente
        ContaCorrente contaCorrenteNova = new ContaCorrente("1111", agencia, saldo, cliente, limite, dataDeVencimento);
        //passa para DAO
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        contaCorrenteDAO.registrarContaCorrente(contaCorrenteNova, cliente);
    }

    public void editarContaController(Conta conta, String tipo, String numeroContaOriginal){
        //recebe os dados a serem editados
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(numeroContaOriginal);

        //passa a instancia correta da conta para seu DAO em específico
        if(conta instanceof ContaCorrente){
            ContaCorrente contaCorrente = (ContaCorrente) conta;

            contaDAO.alterarConta(tipo, contaCorrente.getLimite(), contaCorrente.getDataVencimento(), contaCorrente.getNumeroConta(), numeroContaOriginal, idConta);
        } else{
            LocalDate vazio = LocalDate.parse("1001-01-01");
            contaDAO.alterarConta(tipo, 0, vazio, conta.getNumeroConta(), numeroContaOriginal, idConta);
        }
    }

    public void mudarTipoDeContaController(String tipoNovo, String numero, double taxa, double limite, LocalDate dataVencimento){
        //pega o id da conta a ser alterada
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(numero);

        if(tipoNovo.equalsIgnoreCase("CORRENTE")){
            //edita os dados que mudaram
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            contaCorrenteDAO.editarParaContaCorrente(idConta, limite, dataVencimento);
            //deleta o tipo anterior da conta
            contaDAO.deletarConta(idConta, "conta_poupanca");
        } else{
            ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
            contaPoupancaDAO.editarParaContaPoupanca(idConta, taxa);

            contaDAO.deletarConta(idConta, "conta_corrente");
        }
    }
}
