package util;
import java.util.Random;
import java.util.random.*;


public class GerarNumeroConta {
    public String gerarNumero(String tipo){
        Random random = new Random();

        String codigo = "";

        for(int i = 0; i < 10; i++){
            codigo += random.nextInt(1);
        }

        if(tipo.equals("CORRENTE")){
            codigo += "-32";
        } else{
            codigo += "-17";
        }

        return codigo;
    }
}
