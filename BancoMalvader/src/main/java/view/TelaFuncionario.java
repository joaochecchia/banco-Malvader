package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import dao.ClienteDAO;
import dao.ContaDAO;
import dao.FuncionarioDAO;
import dao.LoginDAO;
import model.Cliente;
import model.Conta;
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
            case JOptionPane.YES_OPTION:
                System.out.println("Usuário clicou em Voltar.");
                break;
            case JOptionPane.NO_OPTION:

                nomeUsuario = nomeUsuarioField.getText();
                if (!nomeUsuario.isEmpty()) {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    boolean verificar = clienteDAO.verificarCliente(nomeUsuario);

                    if (verificar) {

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
                            TelaCriarContaPoupanca telaCriarContaPoupanca = new TelaCriarContaPoupanca(nomeUsuario);
                            telaCriarContaPoupanca.setVisible(true);

                            System.out.println("Usuário escolheu Poupança.");
                        } else if (escolhaConta == 1) {
                            TelaCriarContaCorrente telaCriarContaCorrente = new TelaCriarContaCorrente(nomeUsuario);
                            telaCriarContaCorrente.setVisible(true);

                            System.out.println("Usuário escolheu Corrente.");
                        }

                    }

                } else {
                    System.out.println("O campo CPF está vazio.");
                }
                break;
            case JOptionPane.CANCEL_OPTION:
                TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
                telaCadastroCliente.setVisible(true);
                break;
            default:
                System.out.println("Diálogo fechado.");
        }
    }

    private void encerrarConta() {

        String senha = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if ("admin".equals(senha)) {
            JOptionPane.showMessageDialog(this, "Acesso concedido. Deleção de conta permitido.");

            // Criação do JDialog para inserir usuário e senha
            JDialog dialog = new JDialog(this, "Confirmação de Deleção", true);
            dialog.setSize(300, 200);
            dialog.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            // Campo de Usuário
            JLabel lblUsuario = new JLabel("Usuário:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            dialog.add(lblUsuario, gbc);

            JTextField txtUsuario = new JTextField(15);
            gbc.gridx = 1;
            gbc.gridy = 0;
            dialog.add(txtUsuario, gbc);

            // Campo de Senha
            JLabel lblSenha = new JLabel("Senha:");
            gbc.gridx = 0;
            gbc.gridy = 1;
            dialog.add(lblSenha, gbc);

            JPasswordField txtSenha = new JPasswordField(15);
            gbc.gridx = 1;
            gbc.gridy = 1;
            dialog.add(txtSenha, gbc);

            // Botões Confirmar e Fechar
            JButton btnConfirmar = new JButton("Confirmar");
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            dialog.add(btnConfirmar, gbc);

            JButton btnFechar = new JButton("Fechar");
            gbc.gridx = 1;
            gbc.gridy = 2;
            dialog.add(btnFechar, gbc);

            // Ação ao clicar no botão "Confirmar"
            btnConfirmar.addActionListener(e -> {
                String usuario = txtUsuario.getText();
                String senhaDigitada = new String(txtSenha.getPassword());
                // Aqui você pode adicionar a lógica para confirmar a conta
                JOptionPane.showMessageDialog(dialog, "Conta confirmada para: " + usuario);
                dialog.dispose();

                LoginDAO validarUsuario = new LoginDAO();

                if(!validarUsuario.realizarLogin(usuario, senhaDigitada, false)){
                    ContaDAO contaDAO = new ContaDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

                    ArrayList<Conta> contas = contaDAO.getClasConta(cliente);
                    if(!contas.isEmpty()){
                        TelaDeletarConta telaDeletarConta = new TelaDeletarConta(contas);
                    } else{
                        JOptionPane.showMessageDialog(this, "O cliente não tem contas registradas.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } else{
                    JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            });

            btnFechar.addActionListener(e -> dialog.dispose());

            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarDadosConta() {

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(3, 2, 5, 5)); // 3 linhas e 2 colunas com espaço de 5 pixels

        JLabel userLabel = new JLabel("Usuário:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Senha:");
        JPasswordField passField = new JPasswordField(15);

        dialogPanel.add(userLabel);
        dialogPanel.add(userField);
        dialogPanel.add(passLabel);
        dialogPanel.add(passField);

        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Digite Usuário e Senha", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String usuario = userField.getText();
            System.out.println("USIUARIO: " + usuario);
            String senha = new String(passField.getPassword());
            System.out.println("Senha: " + senha);

            LoginDAO loginDAO = new LoginDAO();

            if(!loginDAO.realizarLogin(usuario, senha, false)){
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.getClasseCliente(usuario);

                ContaDAO contaDAO = new ContaDAO();
                ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

                if(!contas.isEmpty()){
                    TelaVisualizarConta telaVisualizarConta = new TelaVisualizarConta(contas);
                } else{
                    JOptionPane.showMessageDialog(this, "Cliente não possui contas.");
                }

            } else{
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            }
        }
    }

    private void consultarDadosCliente() {

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(3, 2, 5, 5)); // 3 linhas e 2 colunas com espaço de 5 pixels

        JLabel userLabel = new JLabel("Usuário:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Senha:");
        JPasswordField passField = new JPasswordField(15);

        dialogPanel.add(userLabel);
        dialogPanel.add(userField);
        dialogPanel.add(passLabel);
        dialogPanel.add(passField);

        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Digite Usuário e Senha", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String usuario = userField.getText();
            System.out.println("USIUARIO: " + usuario);
            String senha = new String(passField.getPassword());
            System.out.println("Senha: " + senha);

            LoginDAO loginDAO = new LoginDAO();

            if(!loginDAO.realizarLogin(usuario, senha, false)){
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.getClasseCliente(usuario);

                TelaVisualizarCliente telaVisualizarCliente = new TelaVisualizarCliente(cliente);
            } else{
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            }
        }
    }

    private void alterarDadosConta() {

        String senha = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if ("admin".equals(senha)) {
            String nome = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getClasseCliente(nome);

            if(cliente != null){
                ContaDAO contaDAO = new ContaDAO();
                ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

                if(!contas.isEmpty()){
                    TelaEditarConta telaEditarConta = new TelaEditarConta(contas);
                    telaEditarConta.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(this, "Usuario nao tem contas cadastradas.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(this, "Cliente não cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
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

        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarRelatorio() {

        JOptionPane.showMessageDialog(this, "Gerar Relatório de Movimentação acionado.");
    }

    public static void main(String[] args) {
        FuncionarioDAO b = new FuncionarioDAO();

        Funcionario a = b.getClassFuncionario("João da Silva");

        SwingUtilities.invokeLater(() -> {

            TelaFuncionario frame = new TelaFuncionario(a);
            frame.setVisible(true);
        });
    }
}
