package com.example.saudejausuarioservice.datasources;

public interface PostoSaudeDataSource {
    void removerPaciente(Long pacienteId);
    void removerProfissionalSaude(Long profissionalId);
}
