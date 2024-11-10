package view;

import model.Cliente;

import javax.swing.*;
import java.awt.*;

public class TelaVisualizarCliente extends JFrame {

    public TelaVisualizarCliente(Cliente cliente) {

        setTitle("Banco Malvader - Visualizar Cliente");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout em coluna
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Label do cliente
        JLabel clienteLabel = new JLabel("Cliente: " + cliente.getNome());
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clienteLabel);
        panel.add(Box.createVerticalStrut(15));

        // Painel de informações do cliente
        JPanel clientePanel = new JPanel(new GridLayout(0, 2, 10, 5));
        clientePanel.setBorder(BorderFactory.createTitledBorder("Informações do Cliente"));

        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel enderecoLabel = new JLabel("Endereço:");
        JLabel telefoneLabel = new JLabel("Telefone:");

        JLabel nomeValor = new JLabel(cliente.getNome());
        JLabel cpfValor = new JLabel(cliente.getCpf());
        JLabel telefoneValor = new JLabel(cliente.getTelefone());

        clientePanel.add(nomeLabel);
        clientePanel.add(nomeValor);
        clientePanel.add(cpfLabel);
        clientePanel.add(cpfValor);
        clientePanel.add(enderecoLabel);
        clientePanel.add(telefoneLabel);
        clientePanel.add(telefoneValor);

        panel.add(clientePanel);
        panel.add(Box.createVerticalStrut(10));

        add(panel);
        setVisible(true);
    }
}
