package controller;

import dao.ContaCorrenteDAO;
import dao.ContaDAO;
import dao.ContaPoupancaDAO;
import model.Conta;
import model.ContaCorrente;

public class RemoverContaController {
    public void removerContaController(Conta conta){

        if(conta instanceof ContaCorrente){
            System.out.println("CONTA CORRENTE");
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            contaCorrenteDAO.deletarContaCorrente(conta);
        } else{
            System.out.println("CONTA POUPANCA");
            ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
            contaPoupancaDAO.deletarContaPoupanca(conta);
        }
    }
}
