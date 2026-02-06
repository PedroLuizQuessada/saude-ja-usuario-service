package com.example.saudejausuarioservice.entidades;

import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import dtos.abstrato.Page;

import java.util.List;
import java.util.Objects;

public class UsuarioEmailPage extends Page<String> {
    public UsuarioEmailPage(int page, int size, List<String> content) {
        super(page, size, content);
        validarUsuarioEmail(content);
    }

    private void validarUsuarioEmail(List<String> content) {
        content.forEach(usuarioEmail -> {
            if (Objects.isNull(usuarioEmail)) {
                throw new BadArgumentException("O usuário deve possuir um e-mail.");
            }
        });
    }
}
