package view;

import controller.RemoverContaController;
import model.Conta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaDeletarConta extends JFrame {

    public TelaDeletarConta(ArrayList<Conta> contas) {
        setTitle("Banco Malvader - Deletar Conta");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Verificar se há contas disponíveis
        if (contas == null || contas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhuma conta disponível para deletar.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            dispose();
            return;
        }

        // Painel principal com layout em coluna
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Label do cliente
        JLabel clienteLabel = new JLabel("Cliente: " + contas.get(0).getCliente().getNome());
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clienteLabel);
        panel.add(Box.createVerticalStrut(15));

        // Exibir informações de cada conta do cliente
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i); // Conta atual
            String agencia = conta.getAgencia();
            String numeroConta = conta.getNumeroConta();
            String senha = conta.getCliente().getSenha().replaceAll(".", "*");

            // Painel da conta
            JPanel contaPanel = new JPanel(new GridLayout(0, 2, 10, 5));
            contaPanel.setBorder(BorderFactory.createTitledBorder("Conta " + (i + 1)));

            JLabel agenciaLabel = new JLabel("Agência:");
            JLabel numeroContaLabel = new JLabel("Número da Conta:");
            JLabel senhaLabel = new JLabel("Senha:");

            JLabel agenciaValor = new JLabel(agencia);
            JLabel numeroContaValor = new JLabel(numeroConta);
            JLabel senhaValor = new JLabel(senha);

            contaPanel.add(agenciaLabel);
            contaPanel.add(agenciaValor);
            contaPanel.add(numeroContaLabel);
            contaPanel.add(numeroContaValor);
            contaPanel.add(senhaLabel);
            contaPanel.add(senhaValor);

            panel.add(contaPanel);
            panel.add(Box.createVerticalStrut(10));

            // Botão de deletar para cada conta
            JButton deletarButton = new JButton("Deletar Conta");
            deletarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Passar a conta atual para o ActionListener
            deletarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RemoverContaController removerContaController = new RemoverContaController();
                    removerContaController.removerContaController(conta);

                    JOptionPane.showMessageDialog(
                            TelaDeletarConta.this,
                            "Conta deletada com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

            panel.add(deletarButton);
            panel.add(Box.createVerticalStrut(15));
        }

        add(panel);
        setVisible(true);
    }
}
