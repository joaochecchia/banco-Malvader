package controller;

import view.LoginScreen;

public class BancoMalvader {
    void iniciarSistema() {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setVisible(true);
    }

    public static void main(String[] args) {
        BancoMalvader banco = new BancoMalvader();
        banco.iniciarSistema();
    }
}