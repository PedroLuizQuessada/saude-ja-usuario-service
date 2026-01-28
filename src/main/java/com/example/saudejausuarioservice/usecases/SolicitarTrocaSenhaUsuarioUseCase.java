package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoContaUsuario;
import com.example.saudejausuarioservice.entidades.SolicitacaoTrocaSenhaUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.SolicitacaoContaUsuarioMapper;
import dtos.requests.SolicitacaoTrocaSenhaUsuarioRequest;

public class SolicitarTrocaSenhaUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;
    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;

    public SolicitarTrocaSenhaUsuarioUseCase(UsuarioGateway usuarioGateway, SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway) {
        this.usuarioGateway = usuarioGateway;
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
    }

    public SolicitacaoTrocaSenhaUsuario executar(SolicitacaoTrocaSenhaUsuarioRequest solicitacaoTrocaSenhaUsuarioRequest) {
        Usuario usuario = usuarioGateway.getUsuarioByEmail(solicitacaoTrocaSenhaUsuarioRequest.email());
        usuario.setSenha(solicitacaoTrocaSenhaUsuarioRequest.novaSenha(), true);
        SolicitacaoContaUsuario solicitacaoContaUsuario = new SolicitacaoContaUsuario();
        SolicitacaoTrocaSenhaUsuario solicitacaoTrocaSenhaUsuario = SolicitacaoContaUsuarioMapper.toEntidade(solicitacaoContaUsuario, usuario);

        return solicitacaoContaUsuarioGateway.criarSolicitacaoTrocaSenhaUsuario(SolicitacaoContaUsuarioMapper.toDto(solicitacaoTrocaSenhaUsuario));
    }
}
