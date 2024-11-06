package view;

import javax.swing.*;
import java.awt.*;

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

        // Adicionando a JLabel para mostrar nome do usuário e cargo
        JLabel usuarioLabel = new JLabel("Usuário: " + funcionario.getNome() + " | Cargo: " + funcionario.getCargo());
        usuarioLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
        panel.add(usuarioLabel);

        // Botões para funcionalidades
        JButton abrirContaButton = new JButton("Abrir Conta");
        JButton encerrarContaButton = new JButton("Encerrar Conta");
        JButton consultarDadosContaButton = new JButton("Consultar Dados da Conta");
        JButton consultarDadosClienteButton = new JButton("Consultar Dados do Cliente");
        JButton alterarDadosContaButton = new JButton("Alterar Dados da Conta");
        JButton alterarDadosClienteButton = new JButton("Alterar Dados do Cliente");
        JButton cadastrarFuncionarioButton = new JButton("Cadastrar Funcionário");
        JButton gerarRelatorioButton = new JButton("Gerar Relatório de Movimentação");

        // Adicionar Action Listeners para cada botão
        abrirContaButton.addActionListener(e -> abrirConta());
        encerrarContaButton.addActionListener(e -> encerrarConta());
        consultarDadosContaButton.addActionListener(e -> consultarDadosConta());
        consultarDadosClienteButton.addActionListener(e -> consultarDadosCliente());
        alterarDadosContaButton.addActionListener(e -> alterarDadosConta());
        alterarDadosClienteButton.addActionListener(e -> alterarDadosCliente());
        cadastrarFuncionarioButton.addActionListener(e -> cadastrarFuncionario());
        gerarRelatorioButton.addActionListener(e -> gerarRelatorio());

        // Adicionando botões ao painel
        panel.add(abrirContaButton);
        panel.add(encerrarContaButton);
        panel.add(consultarDadosContaButton);
        panel.add(consultarDadosClienteButton);
        panel.add(alterarDadosContaButton);
        panel.add(alterarDadosClienteButton);
        panel.add(cadastrarFuncionarioButton);
        panel.add(gerarRelatorioButton);

        // Adicionando painel à janela
        add(panel);
    }

    private void abrirConta() {
        // Lógica para abrir conta
        JOptionPane.showMessageDialog(this, "Abrir Conta acionado.");
    }

    private void encerrarConta() {
        // Lógica para encerrar conta
        JOptionPane.showMessageDialog(this, "Encerrar Conta acionado.");
    }

    private void consultarDadosConta() {
        // Lógica para consultar dados da conta
        JOptionPane.showMessageDialog(this, "Consultar Dados da Conta acionado.");
    }

    private void consultarDadosCliente() {
        // Lógica para consultar dados do cliente
        JOptionPane.showMessageDialog(this, "Consultar Dados do Cliente acionado.");
    }

    private void alterarDadosConta() {
        // Lógica para alterar dados da conta
        JOptionPane.showMessageDialog(this, "Alterar Dados da Conta acionado.");
    }

    private void alterarDadosCliente() {
        // Lógica para alterar dados do cliente
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
        // Lógica para gerar relatório de movimentação
        JOptionPane.showMessageDialog(this, "Gerar Relatório de Movimentação acionado.");
    }

    public static void main(String[] args) {
        FuncionarioDAO b = new FuncionarioDAO();

        Funcionario a = b.getClassFuncionario("guilherme");

        SwingUtilities.invokeLater(() -> {
            // Exemplo de nome de usuário e cargo
            TelaFuncionario frame = new TelaFuncionario(a);
            frame.setVisible(true);
        });
    }
}
