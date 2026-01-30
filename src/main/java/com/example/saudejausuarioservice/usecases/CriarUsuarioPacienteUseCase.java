package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPaciente;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.NotificacaoGateway;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.ConsumirSolicitacaoRequest;
import dtos.requests.EnviarNotificacaoRequest;

public class CriarUsuarioPacienteUseCase {

    private static final String ASSUNTO_NOTIFICACAO = "Criação do usuário Saúde Já.";
    private static final String MENSAGEM_NOTIFICACAO = "Seu usuário do Saúde Já foi criado com sucesso.";

    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;
    private final UsuarioGateway usuarioGateway;
    private final NotificacaoGateway notificacaoGateway;
    private final ConsumirSolicitacaoContaUsuarioUseCase consumirSolicitacaoContaUsuarioUseCase;

    public CriarUsuarioPacienteUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, UsuarioGateway usuarioGateway, NotificacaoGateway notificacaoGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
        this.usuarioGateway = usuarioGateway;
        this.consumirSolicitacaoContaUsuarioUseCase = new ConsumirSolicitacaoContaUsuarioUseCase(solicitacaoContaUsuarioGateway);
        this.notificacaoGateway = notificacaoGateway;
    }

    public Usuario executar(Long id, ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPaciente = solicitacaoContaUsuarioGateway.getSolicitacaoCriacaoUsuarioPacienteById(id);
        Usuario usuario = UsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPaciente);
        consumirSolicitacaoContaUsuarioUseCase.executar(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoContaUsuario().getId(), consumirSolicitacaoRequest);
        Usuario usuarioCriado = usuarioGateway.saveUsuario(UsuarioMapper.toDto(usuario));
        notificacaoGateway.enviarNotificacao(new EnviarNotificacaoRequest(usuarioCriado.getEmail(), ASSUNTO_NOTIFICACAO, MENSAGEM_NOTIFICACAO));
        return usuarioCriado;
    }
}
