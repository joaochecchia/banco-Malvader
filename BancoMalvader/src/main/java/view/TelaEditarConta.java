package view;

import controller.ContaCorrenteController;
import dao.ClienteDAO;
import dao.ContaDAO;
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

            JLabel agenciaValor = new JLabel(conta.getAgencia());
            JLabel numeroContaValor = new JLabel(conta.getNumeroConta());

            contaPanel.add(agenciaLabel);
            contaPanel.add(agenciaValor);
            contaPanel.add(numeroContaLabel);
            contaPanel.add(numeroContaValor);

            panel.add(contaPanel);
            panel.add(Box.createVerticalStrut(10));

            JButton editarButton = new JButton("Editar Dados");
            editarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            editarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new TelaEdicaoDadosConta(conta, numeroContaValor);
                }
            });

            panel.add(editarButton);
            panel.add(Box.createVerticalStrut(15));
        }

        add(panel);
        setVisible(true);
    }

    private class TelaEdicaoDadosConta extends JFrame {
        public TelaEdicaoDadosConta(Conta conta, JLabel numeroContaLabel) {
            setTitle("Banco Malvader - Edição de Conta");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            add(panel);
            panel.setLayout(null);

            JLabel numeroContaLabelEdicao = new JLabel("Número da Conta:");
            numeroContaLabelEdicao.setBounds(10, 20, 120, 25);
            panel.add(numeroContaLabelEdicao);

            JTextField numeroContaText = new JTextField(conta.getNumeroConta());
            numeroContaText.setBounds(140, 20, 165, 25);
            panel.add(numeroContaText);

            JLabel tipoContaLabel = new JLabel("Tipo da Conta:");
            tipoContaLabel.setBounds(10, 50, 120, 25);
            panel.add(tipoContaLabel);

            JTextField tipoContaText = new JTextField(conta instanceof ContaPoupanca ? "Poupança" : "Corrente");
            tipoContaText.setBounds(140, 50, 165, 25);
            panel.add(tipoContaText);

            JTextField vencimentoText = null;
            if (conta instanceof ContaCorrente) {
                JLabel vencimentoLabel = new JLabel("Vencimento:");
                vencimentoLabel.setBounds(10, 80, 120, 25);
                panel.add(vencimentoLabel);

                vencimentoText = new JTextField(((ContaCorrente) conta).getDataVencimento().toString());
                vencimentoText.setBounds(140, 80, 165, 25);
                panel.add(vencimentoText);
            }

            JButton salvarButton = new JButton("Salvar");
            salvarButton.setBounds(140, 120, 120, 25);
            panel.add(salvarButton);

            JTextField finalVencimentoText = vencimentoText;
            salvarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String numeroOriginal = conta.getNumeroConta();
                    conta.setNumeroConta(numeroContaText.getText());

                    if (conta instanceof ContaCorrente && finalVencimentoText != null) {
                        ContaCorrente contaCorrente = (ContaCorrente) conta;
                        contaCorrente.setDataVencimento(LocalDate.parse(finalVencimentoText.getText()));
                        ContaCorrenteController controller = new ContaCorrenteController();

                        controller.editarContaController(contaCorrente, tipoContaText.getText().toUpperCase(), numeroOriginal);
                    } else{
                        ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
                        ContaCorrenteController controller = new ContaCorrenteController();
                        controller.editarContaController(contaPoupanca, tipoContaText.getText().toUpperCase(), numeroOriginal);
                    }

                    if (conta instanceof ContaPoupanca && tipoContaText.getText().equalsIgnoreCase("CORRENTE")) {
                        TelaMudarTipoConta telaMudarTipoConta = new TelaMudarTipoConta("CORRENTE", conta.getNumeroConta());
                    } else if (conta instanceof ContaCorrente && tipoContaText.getText().equalsIgnoreCase("POUPANCA")) {
                        TelaMudarTipoConta telaMudarTipoConta = new TelaMudarTipoConta("POUPANCA", conta.getNumeroConta());
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta alterada com sucesso");
                    }

                    numeroContaLabel.setText(conta.getNumeroConta());

                    dispose();
                }
            });

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> contas = contaDAO.getClasConta(new ClienteDAO().getClasseCliente("hugo12"));

        TelaEditarConta tela = new TelaEditarConta(contas);
        tela.setVisible(true);
    }
}
