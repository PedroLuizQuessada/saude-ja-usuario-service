package com.example.saudejausuarioservice.controllers;

import com.example.saudejausuarioservice.datasources.NotificacaoDataSource;
import com.example.saudejausuarioservice.datasources.SolicitacaoContaUsuarioDataSource;
import com.example.saudejausuarioservice.datasources.UsuarioDataSource;
import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPaciente;
import com.example.saudejausuarioservice.entidades.SolicitacaoTrocaSenhaUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.NotificacaoGateway;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.SolicitacaoContaUsuarioMapper;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import com.example.saudejausuarioservice.usecases.*;
import dtos.requests.ConsumirSolicitacaoRequest;
import dtos.requests.SolicitacaoTrocaSenhaUsuarioRequest;
import dtos.requests.SolicitacaoCriacaoUsuarioPacienteRequest;
import dtos.responses.SolicitacaoCriacaoUsuarioPacienteResponse;
import dtos.responses.SolicitacaoTrocaSenhaUsuarioResponse;
import dtos.responses.UsuarioResponse;

public class SolicitacaoContaUsuarioController {
    private final SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource;
    private final UsuarioDataSource usuarioDataSource;
    private final NotificacaoDataSource notificacaoDataSource;

    public SolicitacaoContaUsuarioController(SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource, UsuarioDataSource usuarioDataSource, NotificacaoDataSource notificacaoDataSource) {
        this.solicitacaoContaUsuarioDataSource = solicitacaoContaUsuarioDataSource;
        this.usuarioDataSource = usuarioDataSource;
        this.notificacaoDataSource = notificacaoDataSource;
    }

    public SolicitacaoCriacaoUsuarioPacienteResponse solicitarCriacaoUsuarioPaciente(SolicitacaoCriacaoUsuarioPacienteRequest solicitacaoCriacaoUsuarioPacienteRequest) {
        SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway = new SolicitacaoContaUsuarioGateway(solicitacaoContaUsuarioDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);

        SolicitarCriacaoUsuarioPacienteUseCase useCase = new SolicitarCriacaoUsuarioPacienteUseCase(solicitacaoContaUsuarioGateway, usuarioGateway, notificacaoGateway);
        SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPaciente = useCase.executar(solicitacaoCriacaoUsuarioPacienteRequest);

        return SolicitacaoContaUsuarioMapper.toResponse(solicitacaoCriacaoUsuarioPaciente);
    }

    public SolicitacaoTrocaSenhaUsuarioResponse solicitarTrocaSenhaUsuario(SolicitacaoTrocaSenhaUsuarioRequest solicitacaoTrocaSenhaUsuarioRequest) {
        SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway = new SolicitacaoContaUsuarioGateway(solicitacaoContaUsuarioDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);

        SolicitarTrocaSenhaUsuarioUseCase useCase = new SolicitarTrocaSenhaUsuarioUseCase(usuarioGateway, solicitacaoContaUsuarioGateway, notificacaoGateway);
        SolicitacaoTrocaSenhaUsuario solicitacaoCriacaoUsuarioPaciente = useCase.executar(solicitacaoTrocaSenhaUsuarioRequest);

        return SolicitacaoContaUsuarioMapper.toResponse(solicitacaoCriacaoUsuarioPaciente);
    }

    public UsuarioResponse criarUsuarioPaciente(Long id, ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway = new SolicitacaoContaUsuarioGateway(solicitacaoContaUsuarioDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);

        CriarUsuarioPacienteUseCase useCase = new CriarUsuarioPacienteUseCase(solicitacaoContaUsuarioGateway, usuarioGateway, notificacaoGateway);
        Usuario usuario = useCase.executar(id, consumirSolicitacaoRequest);

        return UsuarioMapper.toResponse(usuario);
    }

    public void trocarSenhaUsuario(Long id, ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway = new SolicitacaoContaUsuarioGateway(solicitacaoContaUsuarioDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);

        TrocarSenhaUsuarioUseCase useCase = new TrocarSenhaUsuarioUseCase(solicitacaoContaUsuarioGateway, usuarioGateway, notificacaoGateway);
        useCase.executar(id, consumirSolicitacaoRequest);
    }

    public void apagarSolicitacoesContaUsuarioVencidas() {
        SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway = new  SolicitacaoContaUsuarioGateway(solicitacaoContaUsuarioDataSource);
        ApagarSolicitacoesContaUsuarioVencidasUseCase useCase = new ApagarSolicitacoesContaUsuarioVencidasUseCase(solicitacaoContaUsuarioGateway);

        useCase.executar();
    }
}
