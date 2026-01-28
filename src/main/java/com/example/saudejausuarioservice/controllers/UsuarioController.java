package com.example.saudejausuarioservice.controllers;

import com.example.saudejausuarioservice.datasources.SolicitacaoContaUsuarioDataSource;
import com.example.saudejausuarioservice.datasources.TokenDataSource;
import com.example.saudejausuarioservice.datasources.UsuarioDataSource;
import com.example.saudejausuarioservice.entidades.CredenciaisUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.TokenGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.CredenciaisUsuarioMapper;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import com.example.saudejausuarioservice.usecases.*;
import dtos.requests.AtualizarProprioUsuarioRequest;
import dtos.requests.CriarUsuarioRequest;
import dtos.responses.CredenciaisUsuarioResponse;
import dtos.responses.UsuarioResponse;

public class UsuarioController {
    private final UsuarioDataSource usuarioDataSource;
    private final TokenDataSource tokenDataSource;
    private final SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource;

    public UsuarioController(UsuarioDataSource usuarioDataSource, TokenDataSource tokenDataSource, SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
        this.tokenDataSource = tokenDataSource;
        this.solicitacaoContaUsuarioDataSource = solicitacaoContaUsuarioDataSource;
    }

    public void apagarUsuario(Long id) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        ApagarUsuarioUseCase useCase = new ApagarUsuarioUseCase(usuarioGateway);
        useCase.executar(id);
    }

    public void apagarProprioUsuario(String token) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        ApagarProprioUsuarioUseCase useCase = new ApagarProprioUsuarioUseCase(tokenGateway, usuarioGateway);
        useCase.executar(token);
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

        CriarUsuarioUseCase useCase = new CriarUsuarioUseCase(usuarioGateway, solicitacaoContaUsuarioGateway);

        Usuario usuario = useCase.executar(criarUsuarioRequest);

        return UsuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse atualizarProprioUsuario(String token, AtualizarProprioUsuarioRequest atualizarProprioUsuarioRequest) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway = new SolicitacaoContaUsuarioGateway(solicitacaoContaUsuarioDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        AtualizarProprioUsuarioUseCase useCase = new AtualizarProprioUsuarioUseCase(tokenGateway, solicitacaoContaUsuarioGateway, usuarioGateway);

        Usuario usuario = useCase.executar(token, atualizarProprioUsuarioRequest);

        return UsuarioMapper.toResponse(usuario);
    }
}
