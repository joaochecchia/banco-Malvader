package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ClienteController;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TelaCadastroCliente extends JFrame {
    private JLabel erroLabel;

    public TelaCadastroCliente() {
        setTitle("Banco Malvader - Cadastro de Cliente");
        setSize(400, 580); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 80, 25);
        panel.add(nomeLabel);

        JTextField nomeText = new JTextField(20);
        nomeText.setBounds(140, 20, 165, 25);
        panel.add(nomeText);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(10, 50, 80, 25);
        panel.add(cpfLabel);

        JTextField cpfText = new JTextField(20);
        cpfText.setBounds(140, 50, 165, 25);
        panel.add(cpfText);

        JLabel dataNascimentoLabel = new JLabel("Nascimento:");
        dataNascimentoLabel.setBounds(10, 80, 120, 25);
        panel.add(dataNascimentoLabel);

        JTextField dataNascimentoText = new JTextField(10);
        dataNascimentoText.setBounds(140, 80, 165, 25);
        panel.add(dataNascimentoText);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(10, 110, 80, 25);
        panel.add(telefoneLabel);

        JTextField telefoneText = new JTextField(20);
        telefoneText.setBounds(140, 110, 165, 25);
        panel.add(telefoneText);

        JLabel cepLabel = new JLabel("CEP:");
        cepLabel.setBounds(10, 140, 80, 25);
        panel.add(cepLabel);

        JTextField cepText = new JTextField(20);
        cepText.setBounds(140, 140, 165, 25);
        panel.add(cepText);

        JLabel localLabel = new JLabel("Local:");
        localLabel.setBounds(10, 170, 80, 25);
        panel.add(localLabel);

        JTextField localText = new JTextField(20);
        localText.setBounds(140, 170, 165, 25);
        panel.add(localText);

        JLabel numeroCasaLabel = new JLabel("Número:");
        numeroCasaLabel.setBounds(10, 200, 80, 25);
        panel.add(numeroCasaLabel);

        JTextField numeroCasaText = new JTextField(5);
        numeroCasaText.setBounds(140, 200, 165, 25);
        panel.add(numeroCasaText);

        JLabel bairroLabel = new JLabel("Bairro:");
        bairroLabel.setBounds(10, 230, 80, 25);
        panel.add(bairroLabel);

        JTextField bairroText = new JTextField(20);
        bairroText.setBounds(140, 230, 165, 25);
        panel.add(bairroText);

        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setBounds(10, 260, 80, 25);
        panel.add(cidadeLabel);

        JTextField cidadeText = new JTextField(20);
        cidadeText.setBounds(140, 260, 165, 25);
        panel.add(cidadeText);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(10, 290, 80, 25);
        panel.add(estadoLabel);

        JTextField estadoText = new JTextField(20);
        estadoText.setBounds(140, 290, 165, 25);
        panel.add(estadoText);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 320, 80, 25);
        panel.add(senhaLabel);

        JPasswordField senhaText = new JPasswordField(20);
        senhaText.setBounds(140, 320, 165, 25);
        panel.add(senhaText);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(140, 360, 120, 25);
        panel.add(cadastrarButton);

        erroLabel = new JLabel("", SwingConstants.CENTER);
        erroLabel.setForeground(Color.RED);
        erroLabel.setBounds(10, 400, 360, 25);
        panel.add(erroLabel);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erroLabel.setText("");
                try {
                    if (nomeText.getText().isEmpty() || cpfText.getText().isEmpty() || dataNascimentoText.getText().isEmpty() ||
                            telefoneText.getText().isEmpty() || cepText.getText().isEmpty() || localText.getText().isEmpty() ||
                            numeroCasaText.getText().isEmpty() || bairroText.getText().isEmpty() || cidadeText.getText().isEmpty() ||
                            estadoText.getText().isEmpty() || new String(senhaText.getPassword()).isEmpty()) {
                        erroLabel.setText("Preencha todos os campos corretamente.");
                        return;
                    }

                    String nome = nomeText.getText();
                    String cpf = cpfText.getText();
                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoText.getText());
                    String telefone = telefoneText.getText();
                    String cep = cepText.getText();
                    String local = localText.getText();
                    int numero = Integer.parseInt(numeroCasaText.getText());
                    String bairro = bairroText.getText();
                    String cidade = cidadeText.getText();
                    String estado = estadoText.getText();
                    String senha = new String(senhaText.getPassword());

                    if (estado.length() > 2) {
                        erroLabel.setText("Preencha o estado com sua UF");
                        return;
                    }

                    ClienteController clienteNovo = new ClienteController();

                    clienteNovo.criarCliente(nome, cpf, dataNascimento, telefone, senha,
                            cep, local, numero, bairro, cidade, estado);

                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Número inválido em campo(s) numérico(s).");
                } catch (DateTimeParseException ex) {
                    erroLabel.setText("Data de nascimento em formato inválido. Use AAAA-MM-DD.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCadastroCliente frame = new TelaCadastroCliente();
            frame.setVisible(true);
        });
    }
}
