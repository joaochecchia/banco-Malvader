package view;

import controller.RelatorioController;
import dao.RelatorioDAO;
import model.Conta;
import dao.ClienteDAO;
import dao.ContaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TelaGerarRelatorio extends JFrame {

    public TelaGerarRelatorio(ArrayList<Conta> contas, String nomeCliente) {
        setTitle("Banco Malvader - Gerar Relatório");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel clienteLabel = new JLabel("Relatório de Contas");
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clienteLabel);
        panel.add(Box.createVerticalStrut(15));

        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            JPanel contaPanel = new JPanel(new GridLayout(0, 2, 10, 5));
            contaPanel.setBorder(BorderFactory.createTitledBorder("Conta " + (i + 1)));

            JLabel agenciaLabel = new JLabel("Agência:");
            JLabel numeroContaLabel = new JLabel("Número da Conta:");

            JLabel agenciaValor = new JLabel(conta.getAgencia());
            JLabel numeroContaValor = new JLabel(conta.getNumeroConta());

            contaPanel.add(agenciaLabel);
            contaPanel.add(agenciaValor);
            contaPanel.add(numeroContaLabel);
            contaPanel.add(numeroContaValor);

            panel.add(contaPanel);
            panel.add(Box.createVerticalStrut(10));

            JButton gerarRelatorioButton = new JButton("Gerar Relatório");
            gerarRelatorioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            gerarRelatorioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new TelaEscolherDiretorio(conta, nomeCliente);
                }
            });

            panel.add(gerarRelatorioButton);
            panel.add(Box.createVerticalStrut(15));
        }

        add(panel);
        setVisible(true);
    }

    private class TelaEscolherDiretorio extends JFrame {
        public TelaEscolherDiretorio(Conta conta, String nomeCliente) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                String caminho = selectedDirectory.getAbsolutePath();
                RelatorioController relatorioController = new RelatorioController();

                relatorioController.relatorioController(caminho, conta.getNumeroConta(), nomeCliente);

                JOptionPane.showMessageDialog(null, "Arquivo criado em: " + caminho);
            }
        }
    }

    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> contas = contaDAO.getClasConta(new ClienteDAO().getClasseCliente("hugo12"));

        TelaGerarRelatorio tela = new TelaGerarRelatorio(contas, "hugo12");
        tela.setVisible(true);
    }
}
