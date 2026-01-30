package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.NotificacaoGateway;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.CriarUsuarioRequest;
import dtos.requests.EnviarNotificacaoRequest;

public class CriarUsuarioUseCase {

    private static final String ASSUNTO_NOTIFICACAO = "Criação do usuário Saúde Já.";
    private static final String MENSAGEM_NOTIFICACAO = "Seu usuário do Saúde Já foi criado com sucesso.";
    private final UsuarioGateway usuarioGateway;
    private final NotificacaoGateway notificacaoGateway;
    private final ConferirDisponibilidadeEmailUsuarioUseCase conferirDisponibilidadeEmailUsuarioUseCase;

    public CriarUsuarioUseCase(UsuarioGateway usuarioGateway, SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, NotificacaoGateway notificacaoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.notificacaoGateway = notificacaoGateway;
        this.conferirDisponibilidadeEmailUsuarioUseCase = new ConferirDisponibilidadeEmailUsuarioUseCase(solicitacaoContaUsuarioGateway, usuarioGateway);
    }

    public Usuario executar(CriarUsuarioRequest criarUsuarioRequest) {
        Usuario usuario = UsuarioMapper.toEntidade(criarUsuarioRequest);
        conferirDisponibilidadeEmailUsuarioUseCase.executar(criarUsuarioRequest.email());
        Usuario usuarioCriado = usuarioGateway.saveUsuario(UsuarioMapper.toDto(usuario));
        notificacaoGateway.enviarNotificacao(new EnviarNotificacaoRequest(usuarioCriado.getEmail(), ASSUNTO_NOTIFICACAO, MENSAGEM_NOTIFICACAO));
        return usuarioCriado;
    }
}
