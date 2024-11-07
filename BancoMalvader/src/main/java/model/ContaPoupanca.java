package model;

public class ContaPoupanca extends Conta {
    double taxaDeRendimento;

    public ContaPoupanca(int numero, String agencia, double saldo, Cliente cliente, double taxaDeRendimento){
        super(numero, agencia, saldo, cliente);
        this.taxaDeRendimento = taxaDeRendimento;
    }

    @Override
    public void depositar(double valor){

    };

    @Override
    public boolean sacar(double valor){
        return true;
    }

    public double getTaxaDeRendimento() {
        return taxaDeRendimento;
    }

    public void setTaxaDeRendimento(double taxaDeRendimento) {
        this.taxaDeRendimento = taxaDeRendimento;
    }
}
