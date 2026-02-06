package com.example.saudejausuarioservice.controllers;

import com.example.saudejausuarioservice.datasources.FichaPacienteDataSource;
import com.example.saudejausuarioservice.datasources.NotificacaoDataSource;
import com.example.saudejausuarioservice.datasources.SolicitacaoContaUsuarioDataSource;
import com.example.saudejausuarioservice.datasources.UsuarioDataSource;
import com.example.saudejausuarioservice.entidades.CredenciaisUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.entidades.UsuarioEmailPage;
import com.example.saudejausuarioservice.gateways.FichaPacienteGateway;
import com.example.saudejausuarioservice.gateways.NotificacaoGateway;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.CredenciaisUsuarioMapper;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import com.example.saudejausuarioservice.usecases.*;
import dtos.requests.AtualizarProprioUsuarioRequest;
import dtos.requests.CriarUsuarioRequest;
import dtos.requests.PacienteIdPageRequest;
import dtos.responses.CredenciaisUsuarioResponse;
import dtos.responses.UsuarioEmailPageResponse;
import dtos.responses.UsuarioResponse;

public class UsuarioController {
    private final UsuarioDataSource usuarioDataSource;
    private final SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource;
    private final FichaPacienteDataSource fichaPacienteDataSource;
    private final NotificacaoDataSource notificacaoDataSource;

    public UsuarioController(UsuarioDataSource usuarioDataSource, SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource, FichaPacienteDataSource fichaPacienteDataSource, NotificacaoDataSource notificacaoDataSource) {
        this.usuarioDataSource = usuarioDataSource;
        this.solicitacaoContaUsuarioDataSource = solicitacaoContaUsuarioDataSource;
        this.fichaPacienteDataSource = fichaPacienteDataSource;
        this.notificacaoDataSource = notificacaoDataSource;
    }

    public void apagarUsuario(Long id) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        FichaPacienteGateway fichaPacienteGateway = new FichaPacienteGateway(fichaPacienteDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);

        ApagarUsuarioUseCase useCase = new ApagarUsuarioUseCase(usuarioGateway, fichaPacienteGateway, notificacaoGateway);
        useCase.executar(id);
    }

    public CredenciaisUsuarioResponse getCredenciaisUsuario(String email) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        GetCredenciaisUsuarioUseCase useCase = new GetCredenciaisUsuarioUseCase(usuarioGateway);

        CredenciaisUsuario credenciaisUsuario = useCase.executar(email);

        return CredenciaisUsuarioMapper.toResponse(credenciaisUsuario);
    }

    public UsuarioResponse criarUsuario(CriarUsuarioRequest criarUsuarioRequest) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway = new SolicitacaoContaUsuarioGateway(solicitacaoContaUsuarioDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);

        CriarUsuarioUseCase useCase = new CriarUsuarioUseCase(usuarioGateway, solicitacaoContaUsuarioGateway, notificacaoGateway);

        Usuario usuario = useCase.executar(criarUsuarioRequest);

        return UsuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse atualizarUsuario(Long id, AtualizarProprioUsuarioRequest atualizarProprioUsuarioRequest) {
        SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway = new SolicitacaoContaUsuarioGateway(solicitacaoContaUsuarioDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        AtualizarUsuarioUseCase useCase = new AtualizarUsuarioUseCase(solicitacaoContaUsuarioGateway, usuarioGateway);

        Usuario usuario = useCase.executar(id, atualizarProprioUsuarioRequest);

        return UsuarioMapper.toResponse(usuario);
    }

    public UsuarioEmailPageResponse getUsuarioPacienteEmailFromId(PacienteIdPageRequest pacienteIdPageRequest) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        GetUsuarioPacienteEmailFromIdUseCase useCase = new GetUsuarioPacienteEmailFromIdUseCase(usuarioGateway);

        UsuarioEmailPage usuarioEmailPage = useCase.executar(pacienteIdPageRequest);

        return new UsuarioEmailPageResponse(usuarioEmailPage.getPage(), usuarioEmailPage.getSize(), usuarioEmailPage.getContent());
    }
}
