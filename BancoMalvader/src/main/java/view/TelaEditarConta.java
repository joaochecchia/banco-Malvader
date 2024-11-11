package view;

import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import dao.ContaDAO;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class TelaEditarConta extends JFrame {

    public TelaEditarConta(ArrayList<Conta> contas) {
        setTitle("Banco Malvader - Editar Conta");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel clienteLabel = new JLabel("Cliente: " + contas.get(0).getCliente().getNome());
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clienteLabel);
        panel.add(Box.createVerticalStrut(15));

        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            JPanel contaPanel = new JPanel(new GridLayout(0, 2, 10, 5));
            contaPanel.setBorder(BorderFactory.createTitledBorder("Conta " + (i + 1)));

            JLabel agenciaLabel = new JLabel("Agência:");
            JLabel numeroContaLabel = new JLabel("Número da Conta:");
            JLabel senhaLabel = new JLabel("Senha:");

            JLabel agenciaValor = new JLabel(conta.getAgencia());
            JLabel numeroContaValor = new JLabel(conta.getNumeroConta());
            JLabel senhaValor = new JLabel(conta.getCliente().getSenha().replaceAll(".", "*"));

            contaPanel.add(agenciaLabel);
            contaPanel.add(agenciaValor);
            contaPanel.add(numeroContaLabel);
            contaPanel.add(numeroContaValor);
            contaPanel.add(senhaLabel);
            contaPanel.add(senhaValor);

            panel.add(contaPanel);
            panel.add(Box.createVerticalStrut(10));

            JButton editarButton = new JButton("Editar Dados");
            editarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            editarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editarDadosConta(conta);
                }
            });

            panel.add(editarButton);
            panel.add(Box.createVerticalStrut(15));
        }

        add(panel);
        setVisible(true);
    }

    private void editarDadosConta(Conta conta) {
        String novoNumeroConta = JOptionPane.showInputDialog(this, "Novo Número da Conta:", conta.getNumeroConta());
        String novaSenha = JOptionPane.showInputDialog(this, "Nova Senha:", conta.getCliente().getSenha());
        String novaDataVencimento = JOptionPane.showInputDialog(this, "Nova Data de Vencimento:", conta.getCliente().getSenha());

        try {
            if (novoNumeroConta != null && !novoNumeroConta.isEmpty()) {
                conta.setNumeroConta(novoNumeroConta);
            }
            if (novaSenha != null && !novaSenha.isEmpty()) {
                conta.getCliente().setSenha(novaSenha);
            }
            if (conta instanceof ContaCorrente && novaDataVencimento != null && !novaDataVencimento.isEmpty()) {
                ContaCorrente contaCorrente = (ContaCorrente) conta;
                LocalDate vencimento = LocalDate.parse(novaDataVencimento);

                contaCorrente.setDataVencimento(vencimento);
            }



            JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente("hugo12");

        ArrayList<Conta> a = contaDAO.getClasConta(cliente);

        System.out.println("dadad: " + a.size());

        TelaEditarConta tela = new TelaEditarConta(a);
        tela.setVisible(true);
    }
}

