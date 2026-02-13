package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.SolicitacaoContaUsuario;
import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.mappers.SolicitacaoContaUsuarioMapper;
import dtos.requests.ConsumirSolicitacaoRequest;

import java.time.LocalDateTime;
import java.util.Objects;

public class ConsumirSolicitacaoContaUsuarioUseCase {

    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;

    public ConsumirSolicitacaoContaUsuarioUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
    }

    public void executar(Long id, ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        SolicitacaoContaUsuario solicitacaoContaUsuario = solicitacaoContaUsuarioGateway.getSolicitacaoContaUsuarioById(id);
        if (solicitacaoContaUsuario.isConsumida() || solicitacaoContaUsuario.getValidade().isBefore(LocalDateTime.now()))
            throw new BadArgumentException("Solicitação de conta de usuário inválida.");
        if (!Objects.equals(solicitacaoContaUsuario.getCodigo(), consumirSolicitacaoRequest.codigo()))
            throw new BadArgumentException("Código inválido.");

        solicitacaoContaUsuario.setConsumida(true);

        solicitacaoContaUsuarioGateway.consumirSolicitacaoContaUsuario(SolicitacaoContaUsuarioMapper.toDto(solicitacaoContaUsuario));
    }
}
