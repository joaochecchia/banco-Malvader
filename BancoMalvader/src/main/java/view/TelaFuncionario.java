package view;

import javax.swing.*;
import java.awt.*;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import model.Funcionario;

public class TelaFuncionario extends JFrame {

    public TelaFuncionario(Funcionario funcionario) {
        setTitle("Banco Malvader - Sistema");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Layout em coluna

        JLabel usuarioLabel = new JLabel("Usuário: " + funcionario.getNome() + " | Cargo: " + funcionario.getCargo());
        usuarioLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
        panel.add(usuarioLabel);

        JButton abrirContaButton = new JButton("Abrir Conta");
        JButton encerrarContaButton = new JButton("Encerrar Conta");
        JButton consultarDadosContaButton = new JButton("Consultar Dados da Conta");
        JButton consultarDadosClienteButton = new JButton("Consultar Dados do Cliente");
        JButton alterarDadosContaButton = new JButton("Alterar Dados da Conta");
        JButton alterarDadosClienteButton = new JButton("Alterar Dados do Cliente");
        JButton cadastrarFuncionarioButton = new JButton("Cadastrar Funcionário");
        JButton gerarRelatorioButton = new JButton("Gerar Relatório de Movimentação");

        abrirContaButton.addActionListener(e -> abrirConta());
        encerrarContaButton.addActionListener(e -> encerrarConta());
        consultarDadosContaButton.addActionListener(e -> consultarDadosConta());
        consultarDadosClienteButton.addActionListener(e -> consultarDadosCliente());
        alterarDadosContaButton.addActionListener(e -> alterarDadosConta());
        alterarDadosClienteButton.addActionListener(e -> alterarDadosCliente());
        cadastrarFuncionarioButton.addActionListener(e -> cadastrarFuncionario());
        gerarRelatorioButton.addActionListener(e -> gerarRelatorio());

        panel.add(abrirContaButton);
        panel.add(encerrarContaButton);
        panel.add(consultarDadosContaButton);
        panel.add(consultarDadosClienteButton);
        panel.add(alterarDadosContaButton);
        panel.add(alterarDadosClienteButton);
        panel.add(cadastrarFuncionarioButton);
        panel.add(gerarRelatorioButton);

        add(panel);
    }

    private void abrirConta() {
        JTextField nomeUsuarioField = new JTextField();

        JLabel cpfLabel = new JLabel("Nome completo do Usuário:");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.add(cpfLabel, BorderLayout.NORTH);
        panel.add(nomeUsuarioField, BorderLayout.CENTER);

        Object[] options = {"Voltar", "Confirmar", "Novo cliente"};

        int escolha = JOptionPane.showOptionDialog(
                null,
                panel,
                "Abrir Conta",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]);

        String nomeUsuario = nomeUsuarioField.getText();

        switch (escolha) {
            case JOptionPane.YES_OPTION: // botao de voltar
                System.out.println("Usuário clicou em Voltar.");
                break;
            case JOptionPane.NO_OPTION: // botao de criar conta

                nomeUsuario = nomeUsuarioField.getText();
                if (!nomeUsuario.isEmpty()) {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    boolean verificar = clienteDAO.verificarCliente(nomeUsuario);

                    if (verificar) {
                        // Caixa de diálogo para selecionar o tipo de conta
                        Object[] opcoesConta = {"Poupança", "Corrente"};
                        int escolhaConta = JOptionPane.showOptionDialog(
                                null,
                                "Escolha o tipo de conta:",
                                "Seleção de Tipo de Conta",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opcoesConta,
                                opcoesConta[0]);

                        if (escolhaConta == 0) {
                            TelaCriarContaPoupanca telaCriarContaPoupanca = new TelaCriarContaPoupanca();
                            telaCriarContaPoupanca.setVisible(true);

                            System.out.println("Usuário escolheu Poupança.");
                        } else if (escolhaConta == 1) {
                            TelaCriarContaCorrente telaCriarContaCorrente = new TelaCriarContaCorrente();
                            telaCriarContaCorrente.setVisible(true);

                            System.out.println("Usuário escolheu Corrente.");
                        }

                    }

                } else {
                    System.out.println("O campo CPF está vazio.");
                }
                break;
            case JOptionPane.CANCEL_OPTION: // botao de criar nova conta
                TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
                telaCadastroCliente.setVisible(true);
                break;
            default:
                System.out.println("Diálogo fechado.");
        }
    }

    private void encerrarConta() {

        JOptionPane.showMessageDialog(this, "Encerrar Conta acionado.");
    }

    private void consultarDadosConta() {

        JOptionPane.showMessageDialog(this, "Consultar Dados da Conta acionado.");
    }

    private void consultarDadosCliente() {

        JOptionPane.showMessageDialog(this, "Consultar Dados do Cliente acionado.");
    }

    private void alterarDadosConta() {

        JOptionPane.showMessageDialog(this, "Alterar Dados da Conta acionado.");
    }

    private void alterarDadosCliente() {

        JOptionPane.showMessageDialog(this, "Alterar Dados do Cliente acionado.");
    }

    private void cadastrarFuncionario() {
        String senha = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if ("admin".equals(senha)) {
            JOptionPane.showMessageDialog(this, "Acesso concedido. Cadastro de Funcionário permitido.");

            TelaCadastroFuncionario cadastroFuncionario = new TelaCadastroFuncionario();
            cadastroFuncionario.setVisible(true);

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarRelatorio() {

        JOptionPane.showMessageDialog(this, "Gerar Relatório de Movimentação acionado.");
    }

    public static void main(String[] args) {
        FuncionarioDAO b = new FuncionarioDAO();

        Funcionario a = b.getClassFuncionario("guilherme");

        SwingUtilities.invokeLater(() -> {

            TelaFuncionario frame = new TelaFuncionario(a);
            frame.setVisible(true);
        });
    }
}
