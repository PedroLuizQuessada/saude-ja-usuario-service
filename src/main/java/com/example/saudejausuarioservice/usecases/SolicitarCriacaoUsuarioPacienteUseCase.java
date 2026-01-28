package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoContaUsuario;
import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPaciente;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.SolicitacaoContaUsuarioMapper;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.SolicitacaoCriacaoUsuarioPacienteRequest;

public class SolicitarCriacaoUsuarioPacienteUseCase {
    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;
    private final ConferirDisponibilidadeEmailUsuarioUseCase conferirDisponibilidadeEmailUsuarioUseCase;

    public SolicitarCriacaoUsuarioPacienteUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, UsuarioGateway usuarioGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
        this.conferirDisponibilidadeEmailUsuarioUseCase = new ConferirDisponibilidadeEmailUsuarioUseCase(solicitacaoContaUsuarioGateway, usuarioGateway);
    }

    public SolicitacaoCriacaoUsuarioPaciente executar(SolicitacaoCriacaoUsuarioPacienteRequest solicitacaoCriacaoUsuarioPacienteRequest) {
        UsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteRequest);
        SolicitacaoContaUsuario solicitacaoContaUsuario = new SolicitacaoContaUsuario();
        SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPaciente = SolicitacaoContaUsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteRequest,
                solicitacaoContaUsuario);

        conferirDisponibilidadeEmailUsuarioUseCase.executar(solicitacaoCriacaoUsuarioPacienteRequest.email());

        return solicitacaoContaUsuarioGateway.criarSolicitacaoCriacaoUsuarioPaciente(SolicitacaoContaUsuarioMapper.toDto(solicitacaoCriacaoUsuarioPaciente));
    }
}
