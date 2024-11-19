package dao;

import model.Transacao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RelatorioDAO {
    public void gerarRelatorio(ArrayList<Transacao> transacoes, String pastaDestino, String nomeUsuario) {
        // Criando uma nova pasta de trabalho (workbook)
        Workbook workbook = new XSSFWorkbook();

        // Criando uma nova planilha
        Sheet sheet = workbook.createSheet("Movimentação Bancária: " + nomeUsuario);

        // Criando a linha de cabeçalho
        Row headerRow = sheet.createRow(0);

        // Definindo cabeçalhos
        String[] colunas = {"ID", "Data", "Valor", "Tipo", "Saldo"};
        for (int i = 0; i < colunas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colunas[i]);
        }

        // Preenchendo a planilha com os dados das transações
        int rowNum = 1; // Começa na linha 1 (linha 0 é o cabeçalho)
        for (Transacao transacao : transacoes) {
            Row dataRow = sheet.createRow(rowNum++);

            // Preenchendo cada célula com os valores da transação
            dataRow.createCell(0).setCellValue(transacao.getIdTransacao());
            dataRow.createCell(1).setCellValue(transacao.getData().toString());       // Data
            dataRow.createCell(2).setCellValue(transacao.getValor()); // Descrição
            dataRow.createCell(3).setCellValue(transacao.getTipoTransacao());      // Tipo// Saldo
        }

        // Ajustando automaticamente o tamanho das colunas
        for (int i = 0; i < colunas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Salvando o arquivo na pasta especificada
        try (FileOutputStream fileOut = new FileOutputStream(pastaDestino + "/movimentacao_bancaria.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Arquivo Excel criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fechando a pasta de trabalho
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        ArrayList<Transacao> a = transacaoDAO.extratoDAO(28);

        RelatorioDAO relatorioDAO = new RelatorioDAO();

        // Defina a pasta onde o arquivo será salvo
        String pastaDestino = "C:/Users/jjgab/Documents"; // Exemplo de diretório

        relatorioDAO.gerarRelatorio(a, pastaDestino, "Hugo12");
    }
}
