package view;

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

    public TelaGerarRelatorio(ArrayList<Conta> contas) {
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
                    new TelaEscolherDiretorio();
                }
            });

            panel.add(gerarRelatorioButton);
            panel.add(Box.createVerticalStrut(15));
        }

        add(panel);
        setVisible(true);
    }

    private class TelaEscolherDiretorio extends JFrame {
        public TelaEscolherDiretorio() {
            setTitle("Banco Malvader - Escolher Diretório");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel instrucoes = new JLabel("Escolha o diretório para salvar o relatório:");
            instrucoes.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(instrucoes);
            panel.add(Box.createVerticalStrut(10));

            JButton escolherDiretorioButton = new JButton("Escolher Diretório");
            escolherDiretorioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            escolherDiretorioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int result = fileChooser.showOpenDialog(null);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedDirectory = fileChooser.getSelectedFile();
                        String caminho = selectedDirectory.getAbsolutePath();
                        System.out.println(caminho);
                        JOptionPane.showMessageDialog(null, "Diretório selecionado: " + caminho);
                    }
                }
            });
            panel.add(escolherDiretorioButton);
            panel.add(Box.createVerticalStrut(10));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton gerarButton = new JButton("Gerar");
            gerarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");
                    dispose();
                }
            });
            buttonPanel.add(gerarButton);

            JButton cancelarButton = new JButton("Cancelar");
            cancelarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            buttonPanel.add(cancelarButton);

            panel.add(buttonPanel);

            add(panel);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> contas = contaDAO.getClasConta(new ClienteDAO().getClasseCliente("hugo12"));

        TelaGerarRelatorio tela = new TelaGerarRelatorio(contas);
        tela.setVisible(true);
    }
}
