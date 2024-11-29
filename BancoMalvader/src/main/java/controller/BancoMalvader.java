package controller;

import view.TelaLogin;

public class BancoMalvader {
    void iniciarSistema() {
        TelaLogin loginScreen = new TelaLogin();
        loginScreen.setVisible(true);
    }

    public static void main(String[] args) {
        BancoMalvader banco = new BancoMalvader();
        banco.iniciarSistema();
    }
}