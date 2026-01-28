package com.example.saudejausuarioservice.dtos;

import java.time.LocalDateTime;

public record SolicitacaoContaUsuarioDto(Long id, String codigo, LocalDateTime validade, boolean consumida) {
}
