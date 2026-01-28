package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.CriarUsuarioRequest;

public class CriarUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;
    private final ConferirDisponibilidadeEmailUsuarioUseCase conferirDisponibilidadeEmailUsuarioUseCase;

    public CriarUsuarioUseCase(UsuarioGateway usuarioGateway, SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway) {
        this.usuarioGateway = usuarioGateway;
        this.conferirDisponibilidadeEmailUsuarioUseCase = new ConferirDisponibilidadeEmailUsuarioUseCase(solicitacaoContaUsuarioGateway, usuarioGateway);
    }

    public Usuario executar(CriarUsuarioRequest criarUsuarioRequest) {
        Usuario usuario = UsuarioMapper.toEntidade(criarUsuarioRequest);
        conferirDisponibilidadeEmailUsuarioUseCase.executar(criarUsuarioRequest.email());
        return usuarioGateway.saveUsuario(UsuarioMapper.toDto(usuario));
    }
}
