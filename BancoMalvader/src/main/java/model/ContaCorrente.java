package model;

import java.time.LocalDate;

public class ContaCorrente extends Conta {
    private double limite;
    private LocalDate dataVencimento;

    public ContaCorrente(int numero, String agencia, double saldo, Cliente cliente, double limite, LocalDate dataVencimento) {
        super(numero, agencia, saldo, cliente); // Chama o construtor da classe pai (Conta)
        this.limite = limite;
        this.dataVencimento = dataVencimento;
    }

    @Override
    public void depositar(double valor){

    };

    @Override
    public boolean sacar(double valor){
        return true;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
