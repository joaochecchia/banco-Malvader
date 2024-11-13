package view;
import javax.swing.*;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.LoginDAO;
import model.Cliente;
import model.Funcionario;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Banco Malvader - Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JCheckBox employeeCheckBox = new JCheckBox("Funcionário");
        employeeCheckBox.setBounds(100, 80, 100, 25);
        panel.add(employeeCheckBox);

        loginButton.addActionListener(e -> {
            String usuario = userText.getText();
            String senha = new String(passwordText.getPassword());
            boolean isEmployee = employeeCheckBox.isSelected();

            LoginDAO login = new LoginDAO();
            boolean verify = login.realizarLogin(usuario, senha, isEmployee);

            if (verify) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");

                if(isEmployee){
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    Funcionario funcionario = funcionarioDAO.getClassFuncionario(usuario);

                    TelaMenuFuncionario telaFuncionario = new TelaMenuFuncionario(funcionario);
                    telaFuncionario.setVisible(true);
                    dispose();
                } else{
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

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
