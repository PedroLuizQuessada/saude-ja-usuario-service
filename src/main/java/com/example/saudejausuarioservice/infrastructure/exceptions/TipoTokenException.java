package com.example.saudejausuarioservice.infrastructure.exceptions;

public class TipoTokenException extends RuntimeException {
    public TipoTokenException() {
        super("Credenciais de acesso inválidas.");
    }
}
