package com.example.saudejausuarioservice.datasources;

public interface AutenticacaoDataSource {
    String gerarTokenServico(String audiencia);
}
