package com.example.saudejausuarioservice.gateways;

import com.example.saudejausuarioservice.datasources.PostoSaudeDataSource;

public class PostoSaudeGateway {

    private final PostoSaudeDataSource postoSaudeDataSource;

    public PostoSaudeGateway(PostoSaudeDataSource postoSaudeDataSource) {
        this.postoSaudeDataSource = postoSaudeDataSource;
    }

    public void removerPaciente(Long pacienteId) {
        postoSaudeDataSource.removerPaciente(pacienteId);
    }

    public void removerProfissionalSaude(Long profissionalSaude) {
        postoSaudeDataSource.removerProfissionalSaude(profissionalSaude);
    }
}
