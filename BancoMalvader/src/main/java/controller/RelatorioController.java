package controller;

import dao.ContaDAO;
import dao.RelatorioDAO;
import dao.TransacaoDAO;
import model.Conta;
import model.Transacao;
//import org.apache.poi.ss.formula.functions;

import java.util.ArrayList;

public class RelatorioController {
    public void relatorioController(String caminho, String numeroConta, String nomeCliente){
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        ContaDAO contaDAO = new ContaDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();

        int idConta = contaDAO.getIDConta(numeroConta);

        ArrayList<Transacao> extrato = transacaoDAO.extratoDAO(idConta);
        relatorioDAO.gerarRelatorio(extrato, caminho, nomeCliente);
    }
}
