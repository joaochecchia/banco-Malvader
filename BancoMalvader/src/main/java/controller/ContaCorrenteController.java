package controller;

import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import model.Cliente;
import model.ContaCorrente;
import util.GerarAgencia;
import util.GerarNumeroConta;

import java.time.LocalDate;

public class ContaCorrenteController {
    public void contaCorrenteController(int numeroConta ,double saldo, double limite
            , LocalDate dataDeVencimento, String nomeCliente){
        GerarAgencia gerarAgencia = new GerarAgencia();

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente(nomeCliente);

        String agencia = gerarAgencia.gerarAgencia(cliente.getEndereco().getEstado());

        ContaCorrente contaCorrenteNova = new ContaCorrente(numeroConta, agencia, saldo, cliente, limite, dataDeVencimento);

        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        contaCorrenteDAO.registrarContaCorrente(contaCorrenteNova, cliente);
    }
}
