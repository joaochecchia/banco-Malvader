package view;

import controller.TransacaoController;
import dao.ClienteDAO;
import dao.ContaDAO;
import dao.TransacaoDAO;
import model.Cliente;
import model.Conta;
import model.ContaPoupanca;
import model.Transacao;
import view.TelaExtrato;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TelaMenuCliente extends JFrame {
    private Map<Conta, JLabel> saldoLabelsMap = new HashMap<>(); // Mapeia contas para suas labels de saldo

    public TelaMenuCliente(Cliente cliente) {
        setTitle("Banco Malvader - Visualizar Conta");
        setSize(400, 325);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            JPanel contaPanel = new JPanel();
            contaPanel.setLayout(new BoxLayout(contaPanel, BoxLayout.Y_AXIS));
            contaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel clienteLabel = new JLabel("Cliente: " + conta.getCliente().getNome());
            clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
            clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contaPanel.add(clienteLabel);
            contaPanel.add(Box.createVerticalStrut(15));

            String agencia = conta.getAgencia();
            String numeroConta = conta.getNumeroConta();
            String senha = conta.getCliente().getSenha().replaceAll(".", "*");

            JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
            infoPanel.setBorder(BorderFactory.createTitledBorder("Informações da Conta"));

            infoPanel.add(new JLabel("Agência:"));
            infoPanel.add(new JLabel(agencia));

            // Exibe o saldo e guarda a referência em saldoLabel
            infoPanel.add(new JLabel("Saldo:"));
            JLabel saldoLabel = new JLabel(String.valueOf(conta.getSaldo()));
            saldoLabelsMap.put(conta, saldoLabel); // Armazena a label no mapa com a conta como chave
            infoPanel.add(saldoLabel);

            infoPanel.add(new JLabel("Número da Conta:"));
            infoPanel.add(new JLabel(numeroConta));
            infoPanel.add(new JLabel("Senha:"));
            infoPanel.add(new JLabel(senha));

            contaPanel.add(infoPanel);
            contaPanel.add(Box.createVerticalStrut(10));

            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            Dimension buttonSize = new Dimension(120, 30);

            JButton visualizarButton = new JButton("Extrato");
            visualizarButton.setPreferredSize(buttonSize);
            visualizarButton.addActionListener(e -> extrato(conta));

            JButton depositoButton = new JButton("Depósito");
            depositoButton.setPreferredSize(buttonSize);
            depositoButton.addActionListener(e -> depositar(conta));

            JButton saqueButton = new JButton("Saque");
            saqueButton.setPreferredSize(buttonSize);
            saqueButton.addActionListener(e -> sacar(conta));

            JButton sairButton = new JButton("Sair");
            sairButton.setPreferredSize(buttonSize);
            sairButton.addActionListener(e -> sair());

            buttonsPanel.add(visualizarButton);
            buttonsPanel.add(depositoButton);
            buttonsPanel.add(saqueButton);
            buttonsPanel.add(sairButton);

            contaPanel.add(buttonsPanel);
            tabbedPane.addTab("Conta " + (i + 1), contaPanel);
        }

        add(tabbedPane);
        setVisible(true);
    }

    private void atualizarSaldoLabel(Conta conta) {
        JLabel saldoLabel = saldoLabelsMap.get(conta); // Obtém a label correspondente à conta
        if (saldoLabel != null) {
            saldoLabel.setText(String.valueOf(conta.getSaldo())); // Atualiza o texto da label
        }
    }

    private void depositar(Conta conta) {
        String input = JOptionPane.showInputDialog(this, "Insira o valor do depósito:", "Depósito", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            try {
                double valor = Double.parseDouble(input);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "Erro: O valor deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    TransacaoController transacaoController = new TransacaoController();
                    transacaoController.depositoController(conta.getNumeroConta(), valor);
                    conta.setSaldo(conta.getSaldo() + valor); // Atualiza saldo da conta
                    atualizarSaldoLabel(conta); // Atualiza a label correta após o depósito
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: O valor inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void sacar(Conta conta) {
        String input = JOptionPane.showInputDialog(this, "Insira o valor do saque:", "Saque", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            try {
                double valor = Double.parseDouble(input);
                if (valor <= 0 || valor > conta.getSaldo()) {
                    JOptionPane.showMessageDialog(this, "Erro: Valor inválido para saque.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    TransacaoController transacaoController = new TransacaoController();
                    transacaoController.saque(conta.getNumeroConta(), valor);
                    conta.setSaldo(conta.getSaldo() - valor); // Atualiza saldo da conta
                    atualizarSaldoLabel(conta); // Atualiza a label correta após o saque
                    JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: O valor inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void extrato(Conta conta) {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        ContaDAO contaDAO = new ContaDAO();

        int idConta = contaDAO.getIDConta(conta.getNumeroConta());
        ArrayList<Transacao> transacaos = transacaoDAO.extratoDAO(idConta);

        TelaExtrato telaExtrato = new TelaExtrato(transacaos, conta.getSaldo());
        telaExtrato.setVisible(true);
    }

    private void sair(){
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        dispose();
    }
}
