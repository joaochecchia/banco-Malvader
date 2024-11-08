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

        System.out.println(numeroConta);

        ContaCorrente contaCorrenteNova = new ContaCorrente("1asd", agencia, saldo, cliente, limite, dataDeVencimento);

        System.out.println("ID do cliente:           " + cliente.getId());




        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        contaCorrenteDAO.registrarContaCorrente(contaCorrenteNova, cliente);
    }

    public static void main(String[] args) {
        ContaCorrenteController c = new ContaCorrenteController();

        LocalDate a = LocalDate.parse("2004-05-02");

        c.contaCorrenteController(12312, 100, a, "lukas");
    }
}
