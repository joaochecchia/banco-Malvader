package view;

import javax.swing.*;
import java.awt.*;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.LoginDAO;
import model.Cliente;
import model.Funcionario;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Banco Malvader - Login");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        // Adiciona a imagem redimensionada acima dos campos
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\jjgab\\OneDrive\\Documentos\\GitHub\\banco-Malvader\\BancoMalvader\\src\\main\\resources\\ImageIcon.png"); // Substitua pelo caminho correto
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH); // Define a largura e altura
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(110, 10, 64, 64); // Ajusta a posição da imagem
        panel.add(imageLabel);

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10, 90, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 90, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 120, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 120, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 160, 80, 25);
        panel.add(loginButton);

        JCheckBox employeeCheckBox = new JCheckBox("Funcionário");
        employeeCheckBox.setBounds(100, 160, 100, 25);
        panel.add(employeeCheckBox);

        loginButton.addActionListener(e -> {
            String usuario = userText.getText();
            String senha = new String(passwordText.getPassword());
            boolean isEmployee = employeeCheckBox.isSelected();

            LoginDAO login = new LoginDAO();
            boolean verify = login.realizarLogin(usuario, senha, isEmployee);

            if (verify) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");

                if (isEmployee) {
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    Funcionario funcionario = funcionarioDAO.getClassFuncionario(usuario);

                    TelaMenuFuncionario telaFuncionario = new TelaMenuFuncionario(funcionario);
                    telaFuncionario.setVisible(true);
                    dispose();
                } else {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

                    TelaMenuCliente telaMenuCliente = new TelaMenuCliente(cliente);
                    telaMenuCliente.setVisible(true);
                    dispose();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen frame = new LoginScreen();
            frame.setVisible(true);
        });
    }
}
