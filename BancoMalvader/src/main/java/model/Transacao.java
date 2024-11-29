package model;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Transacao {
    private int idTransacao;
    private String tipoTransacao;
    private double valor;
    private LocalDateTime data;

    public Transacao(int idTransacao, String tipoTransacao, double valor, LocalDateTime data) {
        this.idTransacao = idTransacao;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.data = data;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "idTransacao=" + idTransacao +
                ", tipoTransacao='" + tipoTransacao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }
}
