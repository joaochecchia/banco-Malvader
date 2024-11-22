package view;

import javax.swing.*;
import java.awt.*;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.LoginDAO;
import model.Cliente;
import model.Funcionario;
import java.io.InputStream;
import java.io.IOException;

public class TelaLogin extends JFrame {
    public TelaLogin() {
        //cria a janela banco malvader
        setTitle("Banco Malvader - Login");//título
        setSize(300, 250);//tamanho
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//terminar a aplicação caso fechada
        setLocationRelativeTo(null);//centralizar a abertura da janela

        JPanel panel = new JPanel();//cria uma painel vazio e genérico para agrupar as classes do swing
        add(panel);
        panel.setLayout(null);//não terá layout

        ImageIcon resizedIcon = loadImageIcon("ImageIcon.png");//chama a função que carrega a imagem

        JLabel imageLabel = new JLabel(resizedIcon);//cria uma Label
        imageLabel.setBounds(110, 10, 64, 64);//seta tamanho e localização
        panel.add(imageLabel);//adiciona a imagem ao panel

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10, 90, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);//cria um campo de texto, que seria do usuario
        userText.setBounds(100, 90, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 120, 80, 25);
        panel.add(passwordLabel);

        //campo de senha
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 120, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");//cria um botão de loggin
        loginButton.setBounds(10, 160, 80, 25);
        panel.add(loginButton);

        JCheckBox employeeCheckBox = new JCheckBox("Funcionário");
        employeeCheckBox.setBounds(100, 160, 100, 25);
        panel.add(employeeCheckBox);

        //gatilho do botão, caso seja clicado, dispara a função abaixo
        loginButton.addActionListener(e -> {
            //pega textos
            String usuario = userText.getText();
            String senha = new String(passwordText.getPassword());
            boolean tipo = employeeCheckBox.isSelected();//caixa de texto que retorna booleano

            LoginDAO login = new LoginDAO();//DAO de login para verificar se o usuario está correto
            boolean verify = login.realizarLogin(usuario, senha, tipo);//DAO retorna um booleano

            if (verify) {
                //caixa de dialogo mostrando sucesso ao logar
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");

                if (tipo) {
                    //se o usuário é funcionario, instancie a classe do funcionario na DAO
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    Funcionario funcionario = funcionarioDAO.getClassFuncionario(usuario);

                    //cria a telaMenuFuncionário
                    TelaMenuFuncionario telaFuncionario = new TelaMenuFuncionario(funcionario);
                    telaFuncionario.setVisible(true);//Mostrar a tela
                    dispose();//fecha a tela de login
                } else {
                    //se o usuário é cliente, instancie a classe do cliente na DAO
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

                    //cria a telaMenuCliente
                    TelaMenuCliente telaMenuCliente = new TelaMenuCliente(cliente);
                    telaMenuCliente.setVisible(true);
                    dispose();
                }

            } else {
                //caixa de dialogo mostrando falha ao logar
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
            }
        });
    }

    private ImageIcon loadImageIcon(String fileName) {
        //busca a imagem na pasta resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) { //se a pasta resources não retornal null
            try {
                ImageIcon icon = new ImageIcon(inputStream.readAllBytes());
                //proporção da imagem
                Image image = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                return new ImageIcon(image);
            } catch (IOException e) {// tratar erros ao redimensionar a imagem
                e.printStackTrace();//printar erro
                return new ImageIcon();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Imagem não encontrada.");
            return new ImageIcon();//retorne null
        }
    }
}
