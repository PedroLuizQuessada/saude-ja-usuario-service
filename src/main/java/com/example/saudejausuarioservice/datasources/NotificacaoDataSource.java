package com.example.saudejausuarioservice.datasources;

import dtos.requests.EnviarNotificacaoRequest;

public interface NotificacaoDataSource {
    void enviarNotificacao(EnviarNotificacaoRequest enviarNotificacaoRequest);
}
