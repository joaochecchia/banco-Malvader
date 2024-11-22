package model;

import view.TelaLogin;

public class BancoMalvader {
    String nome = "Banco Malvader";

    public void iniciarSistema(){
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }

    public static void main(String[] args) {
        BancoMalvader bancoMalvader = new BancoMalvader();

        bancoMalvader.iniciarSistema();
    }
}
