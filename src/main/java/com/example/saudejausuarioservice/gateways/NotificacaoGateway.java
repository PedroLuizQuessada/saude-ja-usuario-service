package com.example.saudejausuarioservice.gateways;

import com.example.saudejausuarioservice.datasources.NotificacaoDataSource;
import dtos.requests.EnviarNotificacaoRequest;

public class NotificacaoGateway {
    private final NotificacaoDataSource notificacaoDataSource;

    public NotificacaoGateway(NotificacaoDataSource notificacaoDataSource) {
        this.notificacaoDataSource = notificacaoDataSource;
    }

    public void enviarNotificacao(EnviarNotificacaoRequest enviarNotificacaoRequest) {
        notificacaoDataSource.enviarNotificacao(enviarNotificacaoRequest);
    }
}
