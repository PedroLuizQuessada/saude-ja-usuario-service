package com.example.saudejausuarioservice.infrastructure.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Recurso não permitido.");
    }
}
