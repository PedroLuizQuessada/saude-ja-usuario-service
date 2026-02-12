package com.example.saudejausuarioservice.controllers;

import com.example.saudejausuarioservice.datasources.*;
import com.example.saudejausuarioservice.entidades.CredenciaisUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.entidades.UsuarioEmailPage;
import com.example.saudejausuarioservice.gateways.*;
import com.example.saudejausuarioservice.mappers.CredenciaisUsuarioMapper;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import com.example.saudejausuarioservice.usecases.*;
import dtos.requests.AtualizarProprioUsuarioRequest;
import dtos.requests.CriarUsuarioRequest;
import dtos.requests.PacienteIdPageRequest;
import dtos.requests.ProfissionalSaudeIdPageRequest;
import dtos.responses.CredenciaisUsuarioResponse;
import dtos.responses.UsuarioEmailPageResponse;
import dtos.responses.UsuarioResponse;

public class UsuarioController {
    private final UsuarioDataSource usuarioDataSource;
    private final SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource;
    private final FichaPacienteDataSource fichaPacienteDataSource;
    private final PostoSaudeDataSource postoSaudeDataSource;
    private final NotificacaoDataSource notificacaoDataSource;

    public UsuarioController(UsuarioDataSource usuarioDataSource, SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource, FichaPacienteDataSource fichaPacienteDataSource, PostoSaudeDataSource postoSaudeDataSource, NotificacaoDataSource notificacaoDataSource) {
        this.usuarioDataSource = usuarioDataSource;
        this.solicitacaoContaUsuarioDataSource = solicitacaoContaUsuarioDataSource;
        this.fichaPacienteDataSource = fichaPacienteDataSource;
        this.postoSaudeDataSource = postoSaudeDataSource;
        this.notificacaoDataSource = notificacaoDataSource;
    }

    public void apagarUsuario(Long id) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        FichaPacienteGateway fichaPacienteGateway = new FichaPacienteGateway(fichaPacienteDataSource);
        PostoSaudeGateway postoSaudeGateway = new PostoSaudeGateway(postoSaudeDataSource);
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);

        ApagarUsuarioUseCase useCase = new ApagarUsuarioUseCase(usuarioGateway, fichaPacienteGateway, postoSaudeGateway, notificacaoGateway);
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

    public UsuarioEmailPageResponse getUsuarioProfissionalSaudeEmailFromId(ProfissionalSaudeIdPageRequest profissionalSaudeIdPageRequest) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        GetUsuarioProfissionalSaudeEmailFromIdUseCase useCase = new GetUsuarioProfissionalSaudeEmailFromIdUseCase(usuarioGateway);

        UsuarioEmailPage usuarioEmailPage = useCase.executar(profissionalSaudeIdPageRequest);

        return new UsuarioEmailPageResponse(usuarioEmailPage.getPage(), usuarioEmailPage.getSize(), usuarioEmailPage.getContent());
    }
}
