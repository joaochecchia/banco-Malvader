package controller;

import dao.ContaCorrenteDAO;
import model.Cliente;
import model.ContaCorrente;
import util.GerarAgencia;
import util.GerarNumeroConta;

import java.time.LocalDate;

public class ContaCorrenteController {
    public void contaCorrenteController(int numeroConta ,double saldo, Cliente cliente, double limite
            , LocalDate dataDeVencimento){
        GerarAgencia gerarAgencia = new GerarAgencia();
        String agencia = gerarAgencia.gerarAgencia(cliente.getEndereco().getEstado());

        ContaCorrente contaCorrenteNova = new ContaCorrente(numeroConta, agencia, saldo, cliente, limite, dataDeVencimento);

        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        contaCorrenteDAO.registrarContaCorrente(contaCorrenteNova, cliente);
    }
}
