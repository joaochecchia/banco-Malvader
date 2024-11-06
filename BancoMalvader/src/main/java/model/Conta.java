package model;

public abstract class Conta {

    protected int numero;
    protected String agencia;
    protected double saldo;
    protected Cliente cliente;

    public Conta(int numero, String agencia, double saldo, Cliente cliente) {
        this.numero = numero;
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

