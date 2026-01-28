package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPaciente;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.ConsumirSolicitacaoRequest;

public class CriarUsuarioPacienteUseCase {

    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;
    private final UsuarioGateway usuarioGateway;
    private final ConsumirSolicitacaoContaUsuarioUseCase consumirSolicitacaoContaUsuarioUseCase;

    public CriarUsuarioPacienteUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, UsuarioGateway usuarioGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
        this.usuarioGateway = usuarioGateway;
        this.consumirSolicitacaoContaUsuarioUseCase = new ConsumirSolicitacaoContaUsuarioUseCase(solicitacaoContaUsuarioGateway);
    }

    public Usuario executar(Long id, ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPaciente = solicitacaoContaUsuarioGateway.getSolicitacaoCriacaoUsuarioPacienteById(id);
        Usuario usuario = UsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPaciente);
        consumirSolicitacaoContaUsuarioUseCase.executar(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoContaUsuario().getId(), consumirSolicitacaoRequest);
        return usuarioGateway.saveUsuario(UsuarioMapper.toDto(usuario));
    }
}
