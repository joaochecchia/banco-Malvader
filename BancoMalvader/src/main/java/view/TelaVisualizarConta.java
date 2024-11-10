package view;

import model.Cliente;
import model.Conta;
import model.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaVisualizarConta extends JFrame {

    public TelaVisualizarConta(ArrayList<Conta> contas) {

        setTitle("Banco Malvader - Visualizar Conta");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout em coluna
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Label do cliente
        JLabel clienteLabel = new JLabel("Cliente: " + contas.get(1).getCliente().getNome());
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clienteLabel);
        panel.add(Box.createVerticalStrut(15));

        for (int i = 0; i < contas.size(); i++) {
            String agencia = contas.get(i).getAgencia();
            String numeroConta = contas.get(i).getNumeroConta();
            String senha = contas.get(i).getCliente().getSenha().replaceAll(".", "*");

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

            // Botão para visualizar informações detalhadas da conta
            JButton visualizarButton = new JButton("Visualizar Informações");
            visualizarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            Conta conta = contas.get(i);
            visualizarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tipo = "";
                    if(conta instanceof ContaPoupanca){
                        tipo = "Poupança";
                    } else{
                        tipo = "Corrente";
                    }

                    // Exibir um diálogo com todas as informações detalhadas da conta
                    JOptionPane.showMessageDialog(
                            TelaVisualizarConta.this,
                            "Agência: " + conta.getAgencia() + "\n" +
                                    "Número da Conta: " + conta.getNumeroConta() + "\n" +
                                    "Saldo: " + conta.getSaldo() + "\n" + // Exemplo de campo adicional
                                    "Tipo: " + tipo + "\n" +
                                    "Senha: " + conta.getCliente().getSenha(), // Mostra a senha real
                            "Informações da Conta",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

            panel.add(visualizarButton);
            panel.add(Box.createVerticalStrut(15));
        }

        add(panel);

        setVisible(true);
    }
}
