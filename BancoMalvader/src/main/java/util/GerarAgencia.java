package util;

public class GerarAgencia {
    public String gerarAgencia(String estado){
        int ascii = estado.charAt(0) + estado.charAt(1);

        String agencia = String.valueOf(ascii * 11) + "-" +String.valueOf(ascii / 10);

        return agencia;
    }
}
