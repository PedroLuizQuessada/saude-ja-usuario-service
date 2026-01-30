package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.gateways.AutenticacaoGateway;

public class GerarTokenServicoUseCase {

    private final AutenticacaoGateway autenticacaoGateway;

    public GerarTokenServicoUseCase(AutenticacaoGateway autenticacaoGateway) {
        this.autenticacaoGateway = autenticacaoGateway;
    }

    public String executar(String audiencia) {
        return autenticacaoGateway.gerarTokenServico(audiencia);
    }
}
