package view;

import controller.ContaCorrenteController;
import dao.ClienteDAO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TelaMudarTipoConta extends JFrame {

    public TelaMudarTipoConta(String tipo, String numeroConta) {
        if (tipo.equalsIgnoreCase("POUPANCA")) {
            new TelaMudarParaContaPoupanca(numeroConta);
        } else {
            new TelaMudarParaContaCorrente(numeroConta);
        }
    }

    public class TelaMudarParaContaPoupanca extends JFrame {
        private JLabel erroLabel;

        public TelaMudarParaContaPoupanca(String numeroConta) {
            setTitle("Banco Malvader - Alterar para Conta Poupança");
            setSize(350, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            add(panel);

            JLabel rendimentoLabel = new JLabel("Taxa de rendimento:");
            rendimentoLabel.setBounds(10, 20, 150, 25);
            panel.add(rendimentoLabel);

            JTextField rendimentoText = new JTextField(20);
            rendimentoText.setBounds(140, 20, 165, 25);
            panel.add(rendimentoText);

            JButton cadastrarButton = new JButton("Alterar");
            cadastrarButton.setBounds(140, 120, 120, 25);
            panel.add(cadastrarButton);

            erroLabel = new JLabel("", SwingConstants.CENTER);
            erroLabel.setForeground(Color.RED);
            erroLabel.setBounds(20, 150, 300, 25);
            panel.add(erroLabel);

            cadastrarButton.addActionListener(e -> {
                erroLabel.setText("");
                try {
                    if (rendimentoText.getText().isEmpty()) {
                        erroLabel.setText("Preencha o campo corretamente.");
                        return;
                    }

                    double rendimento = Double.parseDouble(rendimentoText.getText());

                    ContaCorrenteController contaCorrenteController = new ContaCorrenteController();
                    contaCorrenteController.mudarTipoDeContaController("POUPANCA", numeroConta, rendimento, 0.0, LocalDate.parse("1000-01-01"));

                    JOptionPane.showMessageDialog(this, "Conta alterada para Poupança com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Número inválido no campo de rendimento.");
                }
            });

            setVisible(true);
        }
    }

    public class TelaMudarParaContaCorrente extends JFrame {
        private JLabel erroLabel;

        public TelaMudarParaContaCorrente(String numeroConta) {
            setTitle("Banco Malvader - Alterar para Conta Corrente");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            add(panel);

            JLabel saldoLabel = new JLabel("Saldo:");
            saldoLabel.setBounds(10, 20, 80, 25);
            panel.add(saldoLabel);

            JTextField saldoText = new JTextField(20);
            saldoText.setBounds(140, 20, 165, 25);
            panel.add(saldoText);

            JLabel limiteLabel = new JLabel("Limite:");
            limiteLabel.setBounds(10, 50, 80, 25);
            panel.add(limiteLabel);

            JTextField limiteText = new JTextField(20);
            limiteText.setBounds(140, 50, 165, 25);
            panel.add(limiteText);

            JLabel dataVencimentoLabel = new JLabel("Data Vencimento:");
            dataVencimentoLabel.setBounds(10, 80, 120, 25);
            panel.add(dataVencimentoLabel);

            JTextField dataVencimentoText = new JTextField(10);
            dataVencimentoText.setBounds(140, 80, 165, 25);
            panel.add(dataVencimentoText);

            JButton cadastrarButton = new JButton("Alterar");
            cadastrarButton.setBounds(140, 120, 120, 25);
            panel.add(cadastrarButton);

            erroLabel = new JLabel("", SwingConstants.CENTER);
            erroLabel.setForeground(Color.RED);
            erroLabel.setBounds(20, 150, 300, 25);
            panel.add(erroLabel);

            cadastrarButton.addActionListener(e -> {
                erroLabel.setText("");
                try {
                    if (saldoText.getText().isEmpty() || limiteText.getText().isEmpty() || dataVencimentoText.getText().isEmpty()) {
                        erroLabel.setText("Preencha todos os campos corretamente.");
                        return;
                    }

                    double saldo = Double.parseDouble(saldoText.getText());
                    double limite = Double.parseDouble(limiteText.getText());
                    LocalDate dataVencimento = LocalDate.parse(dataVencimentoText.getText());

                    ContaCorrenteController contaCorrenteController = new ContaCorrenteController();
                    contaCorrenteController.mudarTipoDeContaController("CORRENTE", numeroConta, 0.0, limite, dataVencimento);

                    JOptionPane.showMessageDialog(this, "Conta alterada para Corrente com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Valor inválido em campo numérico.");
                } catch (DateTimeParseException ex) {
                    erroLabel.setText("Formato de data inválido. Use AAAA-MM-DD.");
                }
            });

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        TelaMudarTipoConta telaMudarTipoConta = new TelaMudarTipoConta("POUPANCA", "asda");
    }
}
