package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

import dao.*;
import model.Cliente;
import model.Conta;
import model.Funcionario;
import model.Usuario;

public class TelaMenuFuncionario extends JFrame {

    public TelaMenuFuncionario(Funcionario funcionario) {
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
        String admin = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if (admin != null && admin.equals("admin")) {
            String usuario = JOptionPane.showInputDialog(this, "Digite o nome do usuário:");

            if (usuario != null && !usuario.isEmpty()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                if (usuarioDAO.hasUsuario(usuario)) {
                    ContaDAO contaDAO = new ContaDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

                    ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

                    TelaDeletarConta telaDeletarConta = new TelaDeletarConta(contas);
                    telaDeletarConta.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nome do usuário não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha de administrador incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarDadosConta() {
        String admin = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if (admin != null && admin.equals("admin")) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            String usuario = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

            if (usuario != null && !usuario.isEmpty()) {
                if (usuarioDAO.hasUsuario(usuario)) {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

                    ContaDAO contaDAO = new ContaDAO();
                    ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

                    if (contas != null && !contas.isEmpty()) {
                        TelaVisualizarConta telaVisualizarConta = new TelaVisualizarConta(contas);
                        telaVisualizarConta.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Cliente não possui contas.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nome do cliente não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha de administrador incorreta ou operação cancelada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarDadosCliente() {
        String usuario = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

        if (usuario != null && !usuario.isEmpty()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            if (usuarioDAO.hasUsuario(usuario)) {
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.getClasseCliente(usuario);

                TelaVisualizarCliente telaVisualizarCliente = new TelaVisualizarCliente(cliente);
                telaVisualizarCliente.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nome do cliente não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
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
        String senha = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if (senha != null && senha.equals("admin")) {
            String nome = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

            if (nome != null && !nome.trim().isEmpty()) {
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.getClasseCliente(nome);

                if (cliente != null) {
                    TelaEditarCliente telaEditarCliente = new TelaEditarCliente(cliente);
                    telaEditarCliente.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nome do cliente não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
        String nomeCliente = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

        ContaDAO contaDAO = new ContaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente cliente = clienteDAO.getClasseCliente(nomeCliente);

        if(cliente != null){
            ArrayList<Conta> contas = contaDAO.getClasConta(cliente);
            TelaGerarRelatorio telaGerarRelatorio = new TelaGerarRelatorio(contas, cliente.getNome());
            telaGerarRelatorio.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        FuncionarioDAO b = new FuncionarioDAO();

        Funcionario a = b.getClassFuncionario("João da Silva");

        SwingUtilities.invokeLater(() -> {

            TelaMenuFuncionario frame = new TelaMenuFuncionario(a);
            frame.setVisible(true);
        });
    }
}
