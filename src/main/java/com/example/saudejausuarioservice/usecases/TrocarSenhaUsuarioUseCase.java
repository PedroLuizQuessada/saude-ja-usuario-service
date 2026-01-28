package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoTrocaSenhaUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.ConsumirSolicitacaoRequest;

public class TrocarSenhaUsuarioUseCase {

    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;
    private final UsuarioGateway usuarioGateway;
    private final ConsumirSolicitacaoContaUsuarioUseCase consumirSolicitacaoContaUsuarioUseCase;

    public TrocarSenhaUsuarioUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, UsuarioGateway usuarioGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
        this.usuarioGateway = usuarioGateway;
        this.consumirSolicitacaoContaUsuarioUseCase = new ConsumirSolicitacaoContaUsuarioUseCase(solicitacaoContaUsuarioGateway);
    }

    public void executar(Long id, ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        SolicitacaoTrocaSenhaUsuario solicitacaoTrocaSenhaUsuario = solicitacaoContaUsuarioGateway.getSolicitacaoTrocaSenhaUsuarioById(id);
        Usuario usuario = usuarioGateway.getUsuarioByEmail(solicitacaoTrocaSenhaUsuario.getUsuario().getEmail());
        usuario.setSenha(solicitacaoTrocaSenhaUsuario.getNovaSenha(), false);
        consumirSolicitacaoContaUsuarioUseCase.executar(solicitacaoTrocaSenhaUsuario.getSolicitacaoContaUsuario().getId(), consumirSolicitacaoRequest);
        usuarioGateway.saveUsuario(UsuarioMapper.toDto(usuario));
    }
}
