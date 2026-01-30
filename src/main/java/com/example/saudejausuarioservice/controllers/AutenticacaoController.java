package com.example.saudejausuarioservice.controllers;

import com.example.saudejausuarioservice.datasources.AutenticacaoDataSource;
import com.example.saudejausuarioservice.gateways.AutenticacaoGateway;
import com.example.saudejausuarioservice.usecases.GerarTokenServicoUseCase;

public class AutenticacaoController {

    private final AutenticacaoDataSource autenticacaoDataSource;

    public AutenticacaoController(AutenticacaoDataSource autenticacaoDataSource) {
        this.autenticacaoDataSource = autenticacaoDataSource;
    }

    public String gerarTokenServico(String audiencia) {
        AutenticacaoGateway autenticacaoGateway = new AutenticacaoGateway(autenticacaoDataSource);
        GerarTokenServicoUseCase useCase = new GerarTokenServicoUseCase(autenticacaoGateway);

        return useCase.executar(audiencia);
    }
}
