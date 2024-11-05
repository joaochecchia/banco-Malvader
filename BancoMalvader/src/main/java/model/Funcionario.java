package model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
    private String codigoFuncionario;
    private String cargo;
    private String senha;

    public Funcionario(int id, String nome, String cpf, LocalDate dataDeNascimento, String telefone,
                       Endereco endereco, String codigoFuncionario, String cargo, String senha, String tipo) {
        super(id, nome, cpf, dataDeNascimento, telefone, endereco, tipo);
        this.codigoFuncionario = codigoFuncionario;
        this.cargo = cargo;
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public String getSenha() {
        return senha;
    }
}
