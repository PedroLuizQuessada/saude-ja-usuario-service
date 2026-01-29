package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;

public class ApagarSolicitacoesContaUsuarioVencidasUseCase {
    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;

    public ApagarSolicitacoesContaUsuarioVencidasUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
    }

    public void executar() {
        solicitacaoContaUsuarioGateway.apagarSolicitacoesContaUsuarioVencidas();
    }
}
