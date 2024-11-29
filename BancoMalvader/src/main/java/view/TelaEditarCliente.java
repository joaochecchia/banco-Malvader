package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ClienteController;
import dao.ClienteDAO;
import model.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TelaEditarCliente extends JFrame {
    private JLabel erroLabel;

    public TelaEditarCliente(Cliente cliente) {
        setTitle("Banco Malvader - Edição de Cliente");
        setSize(400, 580);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dispose();
            }
        });

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 80, 25);
        panel.add(nomeLabel);

        JTextField nomeText = new JTextField(cliente.getNome());
        nomeText.setBounds(140, 20, 165, 25);
        panel.add(nomeText);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(10, 50, 80, 25);
        panel.add(cpfLabel);

        JTextField cpfText = new JTextField(cliente.getCpf());
        cpfText.setBounds(140, 50, 165, 25);
        panel.add(cpfText);

        JLabel dataNascimentoLabel = new JLabel("Nascimento:");
        dataNascimentoLabel.setBounds(10, 80, 120, 25);
        panel.add(dataNascimentoLabel);

        JTextField dataNascimentoText = new JTextField(cliente.getDataDeNascimento().toString());
        dataNascimentoText.setBounds(140, 80, 165, 25);
        panel.add(dataNascimentoText);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(10, 110, 80, 25);
        panel.add(telefoneLabel);

        JTextField telefoneText = new JTextField(cliente.getTelefone());
        telefoneText.setBounds(140, 110, 165, 25);
        panel.add(telefoneText);

        JLabel cepLabel = new JLabel("CEP:");
        cepLabel.setBounds(10, 140, 80, 25);
        panel.add(cepLabel);

        JTextField cepText = new JTextField(cliente.getEndereco().getCep());
        cepText.setBounds(140, 140, 165, 25);
        panel.add(cepText);

        JLabel localLabel = new JLabel("Local:");
        localLabel.setBounds(10, 170, 80, 25);
        panel.add(localLabel);

        JTextField localText = new JTextField(cliente.getEndereco().getLocal());
        localText.setBounds(140, 170, 165, 25);
        panel.add(localText);

        JLabel numeroCasaLabel = new JLabel("Número:");
        numeroCasaLabel.setBounds(10, 200, 80, 25);
        panel.add(numeroCasaLabel);

        JTextField numeroCasaText = new JTextField(String.valueOf(cliente.getEndereco().getNumeroCasa()));
        numeroCasaText.setBounds(140, 200, 165, 25);
        panel.add(numeroCasaText);

        JLabel bairroLabel = new JLabel("Bairro:");
        bairroLabel.setBounds(10, 230, 80, 25);
        panel.add(bairroLabel);

        JTextField bairroText = new JTextField(cliente.getEndereco().getBairro());
        bairroText.setBounds(140, 230, 165, 25);
        panel.add(bairroText);

        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setBounds(10, 260, 80, 25);
        panel.add(cidadeLabel);

        JTextField cidadeText = new JTextField(cliente.getEndereco().getCidade());
        cidadeText.setBounds(140, 260, 165, 25);
        panel.add(cidadeText);

        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(10, 290, 80, 25);
        panel.add(estadoLabel);

        JTextField estadoText = new JTextField(cliente.getEndereco().getEstado());
        estadoText.setBounds(140, 290, 165, 25);
        panel.add(estadoText);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 320, 80, 25);
        panel.add(senhaLabel);

        JPasswordField senhaText = new JPasswordField(cliente.getSenha());
        senhaText.setBounds(140, 320, 165, 25);
        panel.add(senhaText);

        JButton cadastrarButton = new JButton("Atualizar");
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

                    if (estadoText.getText().length() > 2) {
                        erroLabel.setText("Preencha o estado com sua UF");
                        return;
                    }

                    cliente.setNome(nomeText.getText());
                    cliente.setCpf(cpfText.getText());
                    cliente.setDataDeNascimento(LocalDate.parse(dataNascimentoText.getText()));
                    cliente.setTelefone(telefoneText.getText());
                    cliente.getEndereco().setCep(cepText.getText());
                    cliente.getEndereco().setLocal(localText.getText());
                    cliente.getEndereco().setNumeroCasa(Integer.parseInt(numeroCasaText.getText()));
                    cliente.getEndereco().setBairro(bairroText.getText());
                    cliente.getEndereco().setCidade(cidadeText.getText());
                    cliente.getEndereco().setEstado(estadoText.getText());
                    cliente.setSenha(new String(senhaText.getPassword()));

                    ClienteController clienteController = new ClienteController();
                    clienteController.editarClienteController(cliente);

                    JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
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
        ClienteDAO clienteDAO =  new ClienteDAO();
        Cliente cliente1 = clienteDAO.getClasseCliente("hugo12");

        TelaEditarCliente telaEditarCliente = new TelaEditarCliente(cliente1);
        telaEditarCliente.setVisible(true);
    }
}
