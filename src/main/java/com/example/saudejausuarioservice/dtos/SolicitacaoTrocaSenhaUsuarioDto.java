package com.example.saudejausuarioservice.dtos;

public record SolicitacaoTrocaSenhaUsuarioDto(Long id, String novaSenha, SolicitacaoContaUsuarioDto solicitacaoContaUsuarioDto, UsuarioDto usuarioDto) {
}
