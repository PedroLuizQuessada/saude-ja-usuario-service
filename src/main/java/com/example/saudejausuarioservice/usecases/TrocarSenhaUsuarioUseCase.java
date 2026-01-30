package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoTrocaSenhaUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.NotificacaoGateway;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.ConsumirSolicitacaoRequest;
import dtos.requests.EnviarNotificacaoRequest;

public class TrocarSenhaUsuarioUseCase {

    private static final String ASSUNTO_NOTIFICACAO = "Troca de senha do usuário Saúde Já.";
    private static final String MENSAGEM_NOTIFICACAO = "A senha do seu usuário do Saúde Já foi atualizada com sucesso.";

    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;
    private final UsuarioGateway usuarioGateway;
    private final NotificacaoGateway notificacaoGateway;
    private final ConsumirSolicitacaoContaUsuarioUseCase consumirSolicitacaoContaUsuarioUseCase;

    public TrocarSenhaUsuarioUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, UsuarioGateway usuarioGateway, NotificacaoGateway notificacaoGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
        this.usuarioGateway = usuarioGateway;
        this.notificacaoGateway = notificacaoGateway;
        this.consumirSolicitacaoContaUsuarioUseCase = new ConsumirSolicitacaoContaUsuarioUseCase(solicitacaoContaUsuarioGateway);
    }

    public void executar(Long id, ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        SolicitacaoTrocaSenhaUsuario solicitacaoTrocaSenhaUsuario = solicitacaoContaUsuarioGateway.getSolicitacaoTrocaSenhaUsuarioById(id);
        Usuario usuario = usuarioGateway.getUsuarioByEmail(solicitacaoTrocaSenhaUsuario.getUsuario().getEmail());
        usuario.setSenha(solicitacaoTrocaSenhaUsuario.getNovaSenha(), false);
        consumirSolicitacaoContaUsuarioUseCase.executar(solicitacaoTrocaSenhaUsuario.getSolicitacaoContaUsuario().getId(), consumirSolicitacaoRequest);
        usuarioGateway.saveUsuario(UsuarioMapper.toDto(usuario));
        notificacaoGateway.enviarNotificacao(new EnviarNotificacaoRequest(usuario.getEmail(), ASSUNTO_NOTIFICACAO, MENSAGEM_NOTIFICACAO));
    }
}
