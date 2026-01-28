package com.example.saudejausuarioservice.infrastructure.exceptions;

public class InvalidJwtException extends RuntimeException {
  public InvalidJwtException() {
    super("Token de acesso inválido");
  }
}
