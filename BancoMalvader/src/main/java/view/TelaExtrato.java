package view;

import dao.TransacaoDAO;
import model.Transacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaExtrato extends JFrame {

    public TelaExtrato(ArrayList<Transacao> transacoes, double saldo) {
        setTitle("Banco Malvader - Extrato de Transações");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout em coluna
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título da tela
        JLabel tituloLabel = new JLabel("Extrato de Transações");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(tituloLabel);
        panel.add(Box.createVerticalStrut(15));

        for (int i = 0; i < transacoes.size(); i++) {
            Transacao transacao = transacoes.get(i);

            // Painel de cada transação
            JPanel transacaoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
            transacaoPanel.setBorder(BorderFactory.createTitledBorder("Transação " + (i + 1)));

            JLabel idTransacaoLabel = new JLabel("ID:");
            JLabel tipoTransacaoLabel = new JLabel("Tipo:");
            JLabel valorLabel = new JLabel("Valor:");
            JLabel dataLabel = new JLabel("Data:");

            JLabel idTransacaoValor = new JLabel(String.valueOf(transacao.getIdTransacao()));
            JLabel tipoTransacaoValor = new JLabel(transacao.getTipoTransacao());
            JLabel valorValor = new JLabel(String.format("%.2f", transacao.getValor()));
            JLabel dataValor = new JLabel(transacao.getData().toString());

            transacaoPanel.add(idTransacaoLabel);
            transacaoPanel.add(idTransacaoValor);
            transacaoPanel.add(tipoTransacaoLabel);
            transacaoPanel.add(tipoTransacaoValor);
            transacaoPanel.add(valorLabel);
            transacaoPanel.add(valorValor);
            transacaoPanel.add(dataLabel);
            transacaoPanel.add(dataValor);

            panel.add(transacaoPanel);
            panel.add(Box.createVerticalStrut(10));

            JButton visualizarButton = new JButton("Visualizar Detalhes");
            visualizarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            visualizarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(
                            TelaExtrato.this,
                            "ID: " + transacao.getIdTransacao() + "\n" +
                                    "Tipo: " + transacao.getTipoTransacao() + "\n" +
                                    "Valor: " + String.format("%.2f", transacao.getValor()) + "\n" +
                                    "Data: " + transacao.getData(),
                            "Detalhes da Transação",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

            panel.add(visualizarButton);
            panel.add(Box.createVerticalStrut(15));
        }

        // Adiciona o painel dentro de um JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        ArrayList<Transacao> a = transacaoDAO.extratoDAO(19);

        TelaExtrato telaExtrato = new TelaExtrato(a, 3700);
        telaExtrato.setVisible(true);
    }
}
