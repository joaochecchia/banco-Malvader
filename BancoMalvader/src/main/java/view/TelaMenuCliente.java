package view;

import model.Funcionario;

import javax.swing.*;
import java.awt.*;

public class TelaMenuCliente extends JFrame {
    public TelaMenuCliente() {
        setTitle("Banco Malvader - Sistema");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Layout em coluna

        //JLabel usuarioLabel = new JLabel("Usuário: " + funcionario.getNome() + " | Cargo: " + funcionario.getCargo());
        //usuarioLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
        //panel.add(usuarioLabel);

        JButton consultarSaldoButton = new JButton("Consultar saldo");
        JButton depositarButton = new JButton("Depositar");
        JButton sacarButton = new JButton("Realizar saque");
        JButton consultarExtratoButton = new JButton("Consultar extrato");
        JButton consultarLimiteButton = new JButton("Consultar Limite");
        JButton sairButton = new JButton("Sair");

        consultarSaldoButton.addActionListener(e -> consultarSaldo());
        depositarButton.addActionListener(e -> depositar());
        sacarButton.addActionListener(e -> sacar());
        consultarExtratoButton.addActionListener(e -> consultarExtrato());
        consultarLimiteButton.addActionListener(e -> consultarLimite());
        sairButton.addActionListener(e -> sair());

        panel.add(consultarSaldoButton);
        panel.add(depositarButton);
        panel.add(sacarButton);
        panel.add(consultarExtratoButton);
        panel.add(sairButton);

        add(panel);
    }

    private void consultarSaldo(){

    }

    private void depositar() {
        String input = JOptionPane.showInputDialog(this, "Depósito", "Digite o valor do depósito", JOptionPane.PLAIN_MESSAGE);

        if (input != null) { // Verifica se o usuário não cancelou a entrada
            try {
                double valor = Double.parseDouble(input);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "Erro: O valor deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Código para realizar o depósito
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void sacar(){

    }

    private void consultarExtrato(){

    }

    private void consultarLimite(){

    }
    private void sair(){

    }

    public static void main(String[] args) {
        TelaMenuCliente telaMenuCliente = new TelaMenuCliente();
        telaMenuCliente.setVisible(true);
    }
}
