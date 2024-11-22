package controller;

import dao.ContaCorrenteDAO;
import dao.ContaDAO;
import dao.ContaPoupancaDAO;
import model.Conta;
import model.ContaCorrente;

public class RemoverContaController {
    public void removerContaController(Conta conta){

        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(conta.getNumeroConta());


        if(conta instanceof ContaCorrente){
            System.out.println("CONTA CORRENTE");
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            contaCorrenteDAO.deletarContaCorrente(idConta);
        } else{
            System.out.println("CONTA POUPANCA");
            ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
            contaPoupancaDAO.deletarContaPoupanca(idConta);
        }
    }
}
