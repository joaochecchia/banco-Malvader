package view;

import controller.TransacaoController;
import dao.ClienteDAO;
import model.Cliente;
import model.Funcionario;

import javax.swing.*;
import java.awt.*;

public class TelaMenuCliente extends JFrame {
    public TelaMenuCliente(Cliente cliente) {
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
        // Criando o painel da janela de depósito
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Criando os rótulos e campos de entrada
        JLabel labelValor = new JLabel("Valor do depósito:");
        JTextField campoValor = new JTextField();

        JLabel labelNumeroConta = new JLabel("Número da conta:");
        JTextField campoNumeroConta = new JTextField();

        panel.add(labelValor);
        panel.add(campoValor);

        panel.add(labelNumeroConta);
        panel.add(campoNumeroConta);

        // Botão para confirmar o depósito
        int resultado = JOptionPane.showConfirmDialog(null, panel, "Depósito", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                double valor = Double.parseDouble(campoValor.getText());
                String numeroConta = campoNumeroConta.getText().trim();

                if (valor <= 0) {
                    JOptionPane.showMessageDialog(null, "Erro: O valor deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }



                if (numeroConta.isEmpty() || !numeroConta.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Erro: Número da conta inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Aqui você pode adicionar a lógica para realizar o depósito usando valor e numeroConta
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: O valor inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void sacar(){
        // Criando o painel da janela de depósito
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Criando os rótulos e campos de entrada
        JLabel labelValor = new JLabel("Valor do saque:");
        JTextField campoValor = new JTextField();

        JLabel labelNumeroConta = new JLabel("Número da conta:");
        JTextField campoNumeroConta = new JTextField();

        panel.add(labelValor);
        panel.add(campoValor);

        panel.add(labelNumeroConta);
        panel.add(campoNumeroConta);

        // Botão para confirmar o depósito
        int resultado = JOptionPane.showConfirmDialog(null, panel, "Depósito", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                double valor = Double.parseDouble(campoValor.getText());
                String numeroConta = campoNumeroConta.getText().trim();

                if (valor <= 0) {
                    JOptionPane.showMessageDialog(null, "Erro: O valor deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (numeroConta.isEmpty() || !numeroConta.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Erro: Número da conta inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Aqui você pode adicionar a lógica para realizar o depósito usando valor e numeroConta
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: O valor inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void consultarExtrato(){

    }

    private void consultarLimite(){

    }
    private void sair(){

    }

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente("hugo12");

        TelaMenuCliente telaMenuCliente = new TelaMenuCliente(cliente);
        telaMenuCliente.setVisible(true);
    }
}
