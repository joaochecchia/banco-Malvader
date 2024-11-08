package model;


import java.time.LocalDate;

public class Cliente extends Usuario {
    String senha;
    int idCliente;

    public Cliente(int id, String nome, String cpf, LocalDate dataDeNascimento, String telefone,
                   Endereco endereco, String tipo, String senha, int idCliente) {
        super(id, nome, cpf, dataDeNascimento, telefone, endereco, tipo);
        this.senha = senha;
        this.idCliente = idCliente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
