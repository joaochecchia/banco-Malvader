package model;

public abstract class Conta {

    protected String numeroConta;
    protected String agencia;
    protected double saldo;
    protected Cliente cliente;

    public Conta(String numeroConta, String agencia, double saldo, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public abstract void depositar(double valor);

    public abstract boolean sacar(double valor);

    public double getSaldo() {
        return saldo;
    }
}

