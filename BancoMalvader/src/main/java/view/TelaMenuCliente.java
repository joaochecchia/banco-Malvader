package view;

import controller.TransacaoController;
import dao.ClienteDAO;
import dao.ContaDAO;
import model.Cliente;
import model.Conta;
import model.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaMenuCliente extends JFrame {

    public TelaMenuCliente(ArrayList<Conta> contas) {
        setTitle("Banco Malvader - Visualizar Conta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criando o painel de abas
        JTabbedPane tabbedPane = new JTabbedPane();

        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            // Painel da conta
            JPanel contaPanel = new JPanel();
            contaPanel.setLayout(new BoxLayout(contaPanel, BoxLayout.Y_AXIS));
            contaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Label do cliente
            JLabel clienteLabel = new JLabel("Cliente: " + conta.getCliente().getNome());
            clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
            clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contaPanel.add(clienteLabel);
            contaPanel.add(Box.createVerticalStrut(15));

            String agencia = conta.getAgencia();
            String numeroConta = conta.getNumeroConta();
            String senha = conta.getCliente().getSenha().replaceAll(".", "*");

            // Labels e valores da conta
            JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
            infoPanel.setBorder(BorderFactory.createTitledBorder("Informações da Conta"));

            infoPanel.add(new JLabel("Agência:"));
            infoPanel.add(new JLabel(agencia));
            infoPanel.add(new JLabel("Número da Conta:"));
            infoPanel.add(new JLabel(numeroConta));
            infoPanel.add(new JLabel("Senha:"));
            infoPanel.add(new JLabel(senha));

            contaPanel.add(infoPanel);
            contaPanel.add(Box.createVerticalStrut(10));

            // Painel de botões
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            Dimension buttonSize = new Dimension(120, 30);

            // Botão para visualizar informações detalhadas da conta
            JButton visualizarButton = new JButton("Extrato");
            visualizarButton.setPreferredSize(buttonSize);
            visualizarButton.addActionListener(e -> extrato());

            // Botão para depósito
            JButton depositoButton = new JButton("Depósito");
            depositoButton.setPreferredSize(buttonSize);
            depositoButton.addActionListener(e -> depositar(conta));

            // Botão para saque
            JButton saqueButton = new JButton("Saque");
            saqueButton.setPreferredSize(buttonSize);
            saqueButton.addActionListener(e -> sacar(conta));

            // Botão para sair
            JButton sairButton = new JButton("Sair");
            sairButton.setPreferredSize(buttonSize);
            sairButton.addActionListener(e -> dispose());

            // Adicionando os botões ao painel de botões
            buttonsPanel.add(visualizarButton);
            buttonsPanel.add(depositoButton);
            buttonsPanel.add(saqueButton);
            buttonsPanel.add(sairButton);

            // Adiciona o painel de botões ao painel da conta
            contaPanel.add(buttonsPanel);

            // Adiciona o painel de cada conta como uma aba
            tabbedPane.addTab("Conta " + (i + 1), contaPanel);
        }

        // Adiciona o painel de abas à janela
        add(tabbedPane);
        setVisible(true);
    }

    private void depositar(Conta conta) {
        // Caixa de diálogo para inserir o valor do depósito
        String input = JOptionPane.showInputDialog(this, "Insira o valor do depósito:", "Depósito", JOptionPane.PLAIN_MESSAGE);

        if (input != null) {
            try {
                double valor = Double.parseDouble(input);

                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "Erro: O valor deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Chama o controlador de transação para realizar o depósito
                    TransacaoController transacaoController = new TransacaoController();
                    transacaoController.depositoController(conta.getNumeroConta(), valor);
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: O valor inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void sacar(Conta conta) {
        // Caixa de diálogo para inserir o valor do depósito
        String input = JOptionPane.showInputDialog(this, "Insira o valor do saque:", "Saque", JOptionPane.PLAIN_MESSAGE);

        if (input != null) {
            try {
                double valor = Double.parseDouble(input);

                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "Erro: O valor deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Chama o controlador de transação para realizar o depósito
                    TransacaoController transacaoController = new TransacaoController();
                    boolean saque = transacaoController.saque(conta.getNumeroConta(), valor);

                    if(saque){
                        JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else{
                        JOptionPane.showMessageDialog(this, "Fundos insuficientes.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: O valor inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void extrato(){

    }

    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente("hugo12");

        ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

        TelaMenuCliente telaTeste = new TelaMenuCliente(contas);
        telaTeste.setVisible(true);
    }
}