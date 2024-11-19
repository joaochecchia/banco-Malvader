package model;

public class ContaPoupanca extends Conta {
    private double taxaDeRendimento;

    public ContaPoupanca(String numero, String agencia, double saldo, Cliente cliente, double taxaDeRendimento){
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

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "taxaDeRendimento=" + taxaDeRendimento +
                ", numeroConta='" + numeroConta + '\'' +
                ", agencia='" + agencia + '\'' +
                ", saldo=" + saldo +
                ", cliente=" + cliente +
                '}';
    }
}
