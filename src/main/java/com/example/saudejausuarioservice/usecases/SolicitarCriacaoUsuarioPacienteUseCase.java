package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoContaUsuario;
import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPaciente;
import com.example.saudejausuarioservice.gateways.NotificacaoGateway;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.SolicitacaoContaUsuarioMapper;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.EnviarNotificacaoRequest;
import dtos.requests.SolicitacaoCriacaoUsuarioPacienteRequest;

public class SolicitarCriacaoUsuarioPacienteUseCase {

    private static final String ASSUNTO_NOTIFICACAO = "Solicitação de criação do usuário Saúde Já.";
    private static final String MENSAGEM_NOTIFICACAO = "O código para criação do seu usuário do Saúde Já é %s.";

    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;
    private final ConferirDisponibilidadeEmailUsuarioUseCase conferirDisponibilidadeEmailUsuarioUseCase;
    private final NotificacaoGateway notificacaoGateway;

    public SolicitarCriacaoUsuarioPacienteUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, UsuarioGateway usuarioGateway, NotificacaoGateway notificacaoGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
        this.notificacaoGateway = notificacaoGateway;
        this.conferirDisponibilidadeEmailUsuarioUseCase = new ConferirDisponibilidadeEmailUsuarioUseCase(solicitacaoContaUsuarioGateway, usuarioGateway);
    }

    public SolicitacaoCriacaoUsuarioPaciente executar(SolicitacaoCriacaoUsuarioPacienteRequest solicitacaoCriacaoUsuarioPacienteRequest) {
        UsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteRequest);
        SolicitacaoContaUsuario solicitacaoContaUsuario = new SolicitacaoContaUsuario();
        SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPaciente = SolicitacaoContaUsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteRequest,
                solicitacaoContaUsuario);

        conferirDisponibilidadeEmailUsuarioUseCase.executar(solicitacaoCriacaoUsuarioPacienteRequest.email());

        SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPacienteCriada =
                solicitacaoContaUsuarioGateway.criarSolicitacaoCriacaoUsuarioPaciente(SolicitacaoContaUsuarioMapper.toDto(solicitacaoCriacaoUsuarioPaciente));
        notificacaoGateway.enviarNotificacao(new EnviarNotificacaoRequest(solicitacaoCriacaoUsuarioPacienteRequest.email(),
                ASSUNTO_NOTIFICACAO, String.format(MENSAGEM_NOTIFICACAO, solicitacaoCriacaoUsuarioPacienteCriada.getSolicitacaoContaUsuario().getCodigo())));
        return solicitacaoCriacaoUsuarioPacienteCriada;
    }
}
