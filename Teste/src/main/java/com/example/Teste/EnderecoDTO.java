package com.example.Teste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String cep;
    private String cidade;
    private String bairro;
    private String local;
    private int numeroCasa;

    public static void main(String[] args) {
        EnderecoDTO e = new EnderecoDTO();
        e.getId();
    }

}
