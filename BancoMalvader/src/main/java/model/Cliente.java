package model;


import java.time.LocalDate;

public class Cliente extends Usuario {
    String senha;

    public Cliente(int id, String nome, String cpf, LocalDate dataDeNascimento, String telefone,
                   Endereco endereco, String tipo, String senha) {
        super(id, nome, cpf, dataDeNascimento, telefone, endereco, tipo);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
