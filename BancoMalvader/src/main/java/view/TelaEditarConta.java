package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditarConta extends JFrame {
    private JLabel erroLabel; // Rótulo para mostrar erros

    public TelaEditarConta() {
        setTitle("Banco Malvader - Editar Conta");
        setSize(350, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel tipoContaLabel = new JLabel("Tipo de Conta:");
        tipoContaLabel.setBounds(10, 20, 120, 25);
        panel.add(tipoContaLabel);

        JTextField tipoContaText = new JTextField(20);
        tipoContaText.setBounds(140, 20, 165, 25);
        panel.add(tipoContaText);

        JLabel limiteLabel = new JLabel("Limite:");
        limiteLabel.setBounds(10, 50, 120, 25);
        panel.add(limiteLabel);

        JTextField limiteText = new JTextField(20);
        limiteText.setBounds(140, 50, 165, 25);
        panel.add(limiteText);

        JLabel vencimentoLabel = new JLabel("Vencimento:");
        vencimentoLabel.setBounds(10, 80, 120, 25);
        panel.add(vencimentoLabel);

        JTextField vencimentoText = new JTextField(10); // Formato: AAAA-MM-DD
        vencimentoText.setBounds(140, 80, 165, 25);
        panel.add(vencimentoText);

        JLabel numeroContaLabel = new JLabel("Número da Conta:");
        numeroContaLabel.setBounds(10, 110, 120, 25);
        panel.add(numeroContaLabel);

        JTextField numeroContaText = new JTextField(20);
        numeroContaText.setBounds(140, 110, 165, 25);
        panel.add(numeroContaText);

        JButton editarButton = new JButton("Salvar Alterações");
        editarButton.setBounds(140, 150, 160, 25);
        panel.add(editarButton);

        erroLabel = new JLabel("", SwingConstants.CENTER);
        erroLabel.setForeground(Color.RED);
        erroLabel.setBounds(10, 190, 360, 25);
        panel.add(erroLabel);

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erroLabel.setText(""); // Limpa a mensagem de erro

                try {
                    // Validações dos campos
                    if (tipoContaText.getText().isEmpty() || limiteText.getText().isEmpty() ||
                            vencimentoText.getText().isEmpty() || numeroContaText.getText().isEmpty()) {
                        erroLabel.setText("Preencha todos os campos corretamente.");
                        return;
                    }

                    String tipoConta = tipoContaText.getText();
                    double limite = Double.parseDouble(limiteText.getText());
                    String vencimento = vencimentoText.getText(); // Assumindo formato AAAA-MM-DD
                    String numeroConta = numeroContaText.getText();

                    JOptionPane.showMessageDialog(null, "Dados da conta atualizados com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Valor inválido em campo numérico.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaEditarConta frame = new TelaEditarConta();
            frame.setVisible(true);
        });
    }
}
