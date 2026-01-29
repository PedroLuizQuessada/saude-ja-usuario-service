package com.example.saudejausuarioservice.infrastructure.rotinas.springscheduling.jobs;

import com.example.saudejausuarioservice.controllers.SolicitacaoContaUsuarioController;
import com.example.saudejausuarioservice.datasources.SolicitacaoContaUsuarioDataSource;
import com.example.saudejausuarioservice.datasources.UsuarioDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("springscheduling")
public class ApagarSolicitacoesContaUsuarioVencidasJob {

    private final SolicitacaoContaUsuarioController solicitacaoContaUsuarioController;

    public ApagarSolicitacoesContaUsuarioVencidasJob(SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource, UsuarioDataSource usuarioDataSource) {
        this.solicitacaoContaUsuarioController = new SolicitacaoContaUsuarioController(solicitacaoContaUsuarioDataSource, usuarioDataSource);
    }

    @Scheduled(cron = "0 40 11 ? * THU", zone = "America/Sao_Paulo")
    public void apagarSolicitacoesContaUsuarioVencidas() {
        log.info("Apagando solicitações de conta de usuários vencidas");
        solicitacaoContaUsuarioController.apagarSolicitacoesContaUsuarioVencidas();
        log.info("Solicitações de conta de usuários vencidas apagadas");
    }

}
