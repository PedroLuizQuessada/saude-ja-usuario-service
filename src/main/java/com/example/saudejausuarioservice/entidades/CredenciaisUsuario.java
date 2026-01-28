package com.example.saudejausuarioservice.entidades;

import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import enums.TipoUsuarioEnum;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CredenciaisUsuario {
    private final String email;
    private final String senha;
    private final TipoUsuarioEnum tipo;

    public CredenciaisUsuario(String email, String senha, TipoUsuarioEnum tipo) {
        String mensagemErro = "";

        try {
            validarEmail(email);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarSenha(senha);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarTipo(tipo);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        if (!mensagemErro.isEmpty())
            throw new BadArgumentException(mensagemErro);

        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email))
            throw new BadArgumentException("O usuário deve possuir um e-mail.");
    }

    private void validarSenha(String senha) {
        if (Objects.isNull(senha))
            throw new BadArgumentException("O usuário deve possuir uma senha.");
    }

    private void validarTipo(TipoUsuarioEnum tipo) {
        if (Objects.isNull(tipo))
            throw new BadArgumentException("O usuário deve possuir um tipo.");
    }
}
