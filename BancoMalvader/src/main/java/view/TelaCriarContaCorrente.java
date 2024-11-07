package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import controller.ContaCorrenteController;
import model.Cliente;
import model.ContaCorrente;

public class TelaCriarContaCorrente extends JFrame {
    private JLabel erroLabel;

    public TelaCriarContaCorrente() {
        setTitle("Banco Malvader - Cadastro de Conta Corrente");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        // Remover as labels e os campos para número e agência

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

        JLabel cpfClienteLabel = new JLabel("CPF do Cliente:");
        cpfClienteLabel.setBounds(10, 110, 120, 25);
        panel.add(cpfClienteLabel);

        JTextField cpfClienteText = new JTextField(20);
        cpfClienteText.setBounds(140, 110, 165, 25);
        panel.add(cpfClienteText);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(115, 150, 120, 25);
        panel.add(cadastrarButton);

        erroLabel = new JLabel("", SwingConstants.CENTER);
        erroLabel.setForeground(Color.RED);
        erroLabel.setBounds(20, 190, 300, 25);
        panel.add(erroLabel);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erroLabel.setText("");
                try {

                    if (saldoText.getText().isEmpty() || limiteText.getText().isEmpty() || dataVencimentoText.getText().isEmpty() || cpfClienteText.getText().isEmpty()) {
                        erroLabel.setText("Preencha todos os campos corretamente.");
                        return;
                    }

                    double saldo = Double.parseDouble(saldoText.getText());
                    double limite = Double.parseDouble(limiteText.getText());
                    LocalDate dataVencimento = LocalDate.parse(dataVencimentoText.getText()); // Formato: YYYY-MM-DD
                    String cpfCliente = cpfClienteText.getText();





                    JOptionPane.showMessageDialog(null, "Conta Corrente cadastrada com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Número ou valor inválido em campo(s) numérico(s).");
                } catch (DateTimeParseException ex) {
                    erroLabel.setText("Data de vencimento em formato inválido. Use AAAA-MM-DD.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCriarContaCorrente frame = new TelaCriarContaCorrente();
            frame.setVisible(true);
        });
    }
}
