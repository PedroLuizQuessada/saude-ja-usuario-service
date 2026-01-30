package com.example.saudejausuarioservice.gateways;

import com.example.saudejausuarioservice.datasources.AutenticacaoDataSource;

public class AutenticacaoGateway {

    private final AutenticacaoDataSource autenticacaoDataSource;

    public AutenticacaoGateway(AutenticacaoDataSource autenticacaoDataSource) {
        this.autenticacaoDataSource = autenticacaoDataSource;
    }

    public String gerarTokenServico(String audiencia) {
        return autenticacaoDataSource.gerarTokenServico(audiencia);
    }
}
