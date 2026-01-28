package com.example.saudejausuarioservice.entidades;

import enums.EstadoEnum;
import lombok.Getter;

@Getter
public class SolicitacaoCriacaoUsuarioPacienteEndereco {
    private final Long id;
    private final EstadoEnum estado;
    private final String cidade;
    private final String bairro;
    private final String rua;
    private final String numero;
    private final String complemento;
    private final String cep;

    public SolicitacaoCriacaoUsuarioPacienteEndereco(Long id, EstadoEnum estado, String cidade, String bairro, String rua, String numero, String complemento, String cep) {
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }
}
