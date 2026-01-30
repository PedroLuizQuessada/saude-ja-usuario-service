package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoContaUsuario;
import com.example.saudejausuarioservice.entidades.SolicitacaoTrocaSenhaUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.NotificacaoGateway;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.SolicitacaoContaUsuarioMapper;
import dtos.requests.EnviarNotificacaoRequest;
import dtos.requests.SolicitacaoTrocaSenhaUsuarioRequest;

public class SolicitarTrocaSenhaUsuarioUseCase {

    private static final String ASSUNTO_NOTIFICACAO = "Solicitação de criação do usuário Saúde Já.";
    private static final String MENSAGEM_NOTIFICACAO = "O código para criação do seu usuário do Saúde Já é %s.";
    private final UsuarioGateway usuarioGateway;
    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;
    private final NotificacaoGateway notificacaoGateway;

    public SolicitarTrocaSenhaUsuarioUseCase(UsuarioGateway usuarioGateway, SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, NotificacaoGateway notificacaoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
        this.notificacaoGateway = notificacaoGateway;
    }

    public SolicitacaoTrocaSenhaUsuario executar(SolicitacaoTrocaSenhaUsuarioRequest solicitacaoTrocaSenhaUsuarioRequest) {
        Usuario usuario = usuarioGateway.getUsuarioByEmail(solicitacaoTrocaSenhaUsuarioRequest.email());
        usuario.setSenha(solicitacaoTrocaSenhaUsuarioRequest.novaSenha(), true);
        SolicitacaoContaUsuario solicitacaoContaUsuario = new SolicitacaoContaUsuario();
        SolicitacaoTrocaSenhaUsuario solicitacaoTrocaSenhaUsuario = SolicitacaoContaUsuarioMapper.toEntidade(solicitacaoContaUsuario, usuario);

        SolicitacaoTrocaSenhaUsuario solicitacaoTrocaSenhaUsuarioCriada =
                solicitacaoContaUsuarioGateway.criarSolicitacaoTrocaSenhaUsuario(SolicitacaoContaUsuarioMapper.toDto(solicitacaoTrocaSenhaUsuario));
        notificacaoGateway.enviarNotificacao(new EnviarNotificacaoRequest(solicitacaoTrocaSenhaUsuarioCriada.getUsuario().getEmail(),
                ASSUNTO_NOTIFICACAO, String.format(MENSAGEM_NOTIFICACAO, solicitacaoTrocaSenhaUsuarioCriada.getSolicitacaoContaUsuario().getCodigo())));
        return solicitacaoTrocaSenhaUsuarioCriada;
    }
}
