package controller;

import dao.ContaDAO;
import dao.RelatorioDAO;
import dao.TransacaoDAO;
import model.Transacao;
//import org.apache.poi.ss.formula.functions;

import java.util.ArrayList;

public class RelatorioController {
    public void relatorioController(String caminho, String numeroConta, double saldo, String nomeCliente){

        TransacaoDAO transacaoDAO = new TransacaoDAO();
        ContaDAO contaDAO = new ContaDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();

        int idConta = contaDAO.getIDConta(numeroConta);

        ArrayList<Transacao> extrato = transacaoDAO.extratoDAO(idConta);

        double[] saldoExtrato = new double[extrato.size()];
        saldoExtrato[saldoExtrato.length - 1] = saldo;

        //algoritimo que calcula o saldo relativo a cada transacão na tela
        //pelo saldo atual da conta
        for(int i = saldoExtrato.length - 2; i >= 0; i--){
            Transacao transacaoA = extrato.get(i);
            if(transacaoA.getTipoTransacao().equals("SAQUE")){
                saldo += transacaoA.getValor();
                saldoExtrato[i] = saldo;
              System.out.println("Saldo2: " + saldo);
        } else{
                saldo -= transacaoA.getValor();
                saldoExtrato[i] = saldo;
               System.out.println("Saldo1: " + saldo);
           }
       }
        //passagem dos dados
        relatorioDAO.gerarRelatorio(extrato, saldoExtrato, caminho, nomeCliente);
    }
}
