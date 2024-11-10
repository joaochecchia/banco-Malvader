package view;

import controller.RemoverContaController;
import dao.ClienteDAO;
import dao.ContaDAO;
import model.Cliente;
import model.Conta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TelaDeletarConta extends JFrame {

    public TelaDeletarConta(ArrayList<Conta> contas) {

        setTitle("Banco Malvader - Deletar Conta");
        setSize(600, 400);
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

        // Exibir informações de cada conta do cliente
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

            // Botão de deletar para cada conta
            JButton deletarButton = new JButton("Deletar Conta");
            deletarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Passar a conta atual para o ActionListener
            Conta conta = contas.get(i);
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

    public static void main(String[] args) {
        // Dados de exemplo
        ClienteDAO cliente = new ClienteDAO();
        Cliente a = cliente.getClasseCliente("hugo12");

        ContaDAO conta = new ContaDAO();
        ArrayList teste = conta.getClasConta(a);

        List<String> agencia = new ArrayList<>();
        List<String> numero = new ArrayList<>();
        List<String> senha = new ArrayList<>();

        agencia.add("agencia 1");
        agencia.add("agencia 2");

        numero.add("numero1");
        numero.add("numero2");

        senha.add("senha1");
        senha.add("senha2");


        String nomeCliente = "João";
        SwingUtilities.invokeLater(() -> {
            new TelaDeletarConta(teste);
        });
    }
}
