package com.example.saudejausuarioservice.dtos;

import enums.TipoUsuarioEnum;

public record UsuarioDto(Long id, String nome, String email, String senha, TipoUsuarioEnum tipo, EnderecoUsuarioDto enderecoUsuario, String celular) {
}
