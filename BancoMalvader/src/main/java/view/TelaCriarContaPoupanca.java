package view;

import model.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCriarContaPoupanca extends JFrame {
    private JLabel erroLabel; // Rótulo para mostrar erros

    public TelaCriarContaPoupanca(String nomeUsuario) {
        setTitle("Banco Malvader - Cadastro de Conta Poupança");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome Cliente:");
        nomeLabel.setBounds(10, 20, 100, 25);
        panel.add(nomeLabel);

        JTextField nomeText = new JTextField(20);
        nomeText.setBounds(140, 20, 165, 25);
        panel.add(nomeText);

        JLabel cpfLabel = new JLabel("CPF Cliente:");
        cpfLabel.setBounds(10, 50, 100, 25);
        panel.add(cpfLabel);

        JTextField cpfText = new JTextField(20);
        cpfText.setBounds(140, 50, 165, 25);
        panel.add(cpfText);

        JLabel saldoLabel = new JLabel("Depósito Inicial:");
        saldoLabel.setBounds(10, 80, 100, 25);
        panel.add(saldoLabel);

        JTextField saldoText = new JTextField(20);
        saldoText.setBounds(140, 80, 165, 25);
        panel.add(saldoText);

        JLabel taxaLabel = new JLabel("Taxa de Rendimento:");
        taxaLabel.setBounds(10, 110, 140, 25);
        panel.add(taxaLabel);

        JTextField taxaText = new JTextField(20);
        taxaText.setBounds(140, 110, 165, 25);
        panel.add(taxaText);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(140, 150, 120, 25);
        panel.add(cadastrarButton);

        erroLabel = new JLabel("", SwingConstants.CENTER);
        erroLabel.setForeground(Color.RED);
        erroLabel.setBounds(37, 180, 250, 25);
        panel.add(erroLabel);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erroLabel.setText(""); // Limpa a mensagem de erro
                try {
                    // Validações dos campos
                    if (nomeText.getText().isEmpty() || cpfText.getText().isEmpty() || saldoText.getText().isEmpty() || taxaText.getText().isEmpty()) {
                        erroLabel.setText("Preencha todos os campos corretamente.");
                        return;
                    }

                    String nomeCliente = nomeText.getText();
                    String cpfCliente = cpfText.getText();
                    double saldoInicial = Double.parseDouble(saldoText.getText());
                    double taxaRendimento = Double.parseDouble(taxaText.getText());



                    JOptionPane.showMessageDialog(null, "Conta Poupança cadastrada com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Preencha os campos numéricos corretamente.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCriarContaPoupanca frame = new TelaCriarContaPoupanca("Lucas");
            frame.setVisible(true);
        });
    }
}
