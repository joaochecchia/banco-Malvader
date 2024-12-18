package com.example.BancoMalvaderWeb.Endereco;

import com.example.BancoMalvaderWeb.Usuario.UsuarioModel;
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
    private UsuarioModel usuario;
}
