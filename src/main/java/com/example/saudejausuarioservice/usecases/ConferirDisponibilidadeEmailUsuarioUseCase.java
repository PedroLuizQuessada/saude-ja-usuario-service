package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;

public class ConferirDisponibilidadeEmailUsuarioUseCase {
    private final SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway;
    private final UsuarioGateway usuarioGateway;

    public ConferirDisponibilidadeEmailUsuarioUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, UsuarioGateway usuarioGateway) {
        this.solicitacaoContaUsuarioGateway = solicitacaoContaUsuarioGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public void executar(String email) {
        if (solicitacaoContaUsuarioGateway.countByEmailWhereIsValidaAndNaoConsumida(email) > 0 ||
                usuarioGateway.countByEmail(email) > 0) {
            throw new BadArgumentException("E-mail já está sendo utilizado.");
        }
    }
}
