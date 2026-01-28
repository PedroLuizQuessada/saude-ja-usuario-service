package com.example.saudejausuarioservice.dtos;

public record SolicitacaoCriacaoUsuarioPacienteDto(Long id, SolicitacaoContaUsuarioDto solicitacaoContaUsuarioDto, String nome, String email, String senha, EnderecoUsuarioDto enderecoUsuario, String celular) {
}
