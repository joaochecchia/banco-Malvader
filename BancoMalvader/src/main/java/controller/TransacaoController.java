package controller;

import dao.ContaDAO;
import dao.TransacaoDAO;

public class TransacaoController {
    public void depositoController(String numeroConta, double valor){
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(numeroConta);

        TransacaoDAO transacaoDAO = new TransacaoDAO();
        transacaoDAO.depositoDAO(valor, idConta);
    }

    public void saque(String numeroContaDestino, double valor){
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(numeroContaDestino);

        TransacaoDAO transacaoDAO = new TransacaoDAO();
        transacaoDAO.saqueDAO(valor, idConta);
    }

    public static void main(String[] args) {
        TransacaoController transacaoController = new TransacaoController();
        transacaoController.depositoController("numero legal", 1000);
    }
}
