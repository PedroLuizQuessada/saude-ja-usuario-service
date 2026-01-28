package com.example.saudejausuarioservice.dtos;

import enums.EstadoEnum;

public record EnderecoUsuarioDto(Long id, EstadoEnum estado, String cidade, String bairro, String rua, String numero, String complemento, String cep) {
}
