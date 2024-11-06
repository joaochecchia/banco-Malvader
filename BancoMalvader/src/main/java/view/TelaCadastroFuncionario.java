package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.FuncionarioDAO;
import model.Endereco;
import model.Funcionario;

import java.time.LocalDate;

public class TelaCadastroFuncionario extends JFrame {
    public TelaCadastroFuncionario() {
        setTitle("Banco Malvader - Cadastro de Funcionário");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 80, 25);
        panel.add(nomeLabel);

        JTextField nomeText = new JTextField(20);
        nomeText.setBounds(150, 20, 165, 25);
        panel.add(nomeText);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(10, 50, 80, 25);
        panel.add(cpfLabel);

        JTextField cpfText = new JTextField(20);
        cpfText.setBounds(100, 50, 165, 25);
        panel.add(cpfText);

        JLabel dataNascimentoLabel = new JLabel("Data Nascimento:");
        dataNascimentoLabel.setBounds(10, 80, 120, 25);
        panel.add(dataNascimentoLabel);

        JTextField dataNascimentoText = new JTextField(10); // Formato: YYYY-MM-DD
        dataNascimentoText.setBounds(140, 80, 125, 25);
        panel.add(dataNascimentoText);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(10, 110, 80, 25);
        panel.add(telefoneLabel);

        JTextField telefoneText = new JTextField(20);
        telefoneText.setBounds(100, 110, 165, 25);
        panel.add(telefoneText);

        JLabel cepLabel = new JLabel("CEP:");
        cepLabel.setBounds(10, 140, 80, 25);
        panel.add(cepLabel);

        JTextField cepText = new JTextField(20);
        cepText.setBounds(100, 140, 165, 25);
        panel.add(cepText);

        JLabel localLabel = new JLabel("Local:");
        localLabel.setBounds(10, 170, 80, 25);
        panel.add(localLabel);

        JTextField localText = new JTextField(20);
        localText.setBounds(100, 170, 165, 25);
        panel.add(localText);

        JLabel numeroCasaLabel = new JLabel("Número:");
        numeroCasaLabel.setBounds(10, 200, 80, 25);
        panel.add(numeroCasaLabel);

        JTextField numeroCasaText = new JTextField(5);
        numeroCasaText.setBounds(100, 200, 50, 25);
        panel.add(numeroCasaText);

        JLabel bairroLabel = new JLabel("Bairro:");
        bairroLabel.setBounds(10, 230, 80, 25);
        panel.add(bairroLabel);

        JTextField bairroText = new JTextField(20);
        bairroText.setBounds(100, 230, 165, 25);
        panel.add(bairroText);

        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setBounds(10, 260, 80, 25);
        panel.add(cidadeLabel);

        JTextField cidadeText = new JTextField(20);
        cidadeText.setBounds(100, 260, 165, 25);
        panel.add(cidadeText);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(10, 290, 80, 25);
        panel.add(estadoLabel);

        JTextField estadoText = new JTextField(20);
        estadoText.setBounds(100, 290, 165, 25);
        panel.add(estadoText);

        JLabel codigoFuncionarioLabel = new JLabel("Código Funcionario:");
        codigoFuncionarioLabel.setBounds(10, 320, 130, 25);
        panel.add(codigoFuncionarioLabel);

        JTextField codigoFuncionarioText = new JTextField(20);
        codigoFuncionarioText.setBounds(150, 320, 165, 25);
        panel.add(codigoFuncionarioText);

        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setBounds(10, 350, 80, 25);
        panel.add(cargoLabel);

        JTextField cargoText = new JTextField(20);
        cargoText.setBounds(100, 350, 165, 25);
        panel.add(cargoText);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 380, 80, 25);
        panel.add(senhaLabel);

        JPasswordField senhaText = new JPasswordField(20);
        senhaText.setBounds(100, 380, 165, 25);
        panel.add(senhaText);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(10, 420, 120, 25);
        panel.add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Coleta os dados inseridos
                String nome = nomeText.getText();
                String cpf = cpfText.getText();
                LocalDate dataNascimento = LocalDate.parse(dataNascimentoText.getText()); // Formato: YYYY-MM-DD
                String telefone = telefoneText.getText();
                String cep = cepText.getText();
                String local = localText.getText();
                int numeroCasa = Integer.parseInt(numeroCasaText.getText());
                String bairro = bairroText.getText();
                String cidade = cidadeText.getText();
                String estado = estadoText.getText();
                String codigoFuncionario = codigoFuncionarioText.getText();
                String cargo = cargoText.getText();
                String senha = new String(senhaText.getPassword());

                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                dispose(); // Fecha a tela de cadastro
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
            frame.setVisible(true);
        });
    }
}

