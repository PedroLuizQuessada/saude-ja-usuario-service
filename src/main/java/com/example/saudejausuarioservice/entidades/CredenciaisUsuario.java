package com.example.saudejausuarioservice.entidades;

import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import enums.TipoUsuarioEnum;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CredenciaisUsuario {
    private final Long id;
    private final String senha;
    private final TipoUsuarioEnum tipo;

    public CredenciaisUsuario(Long id, String senha, TipoUsuarioEnum tipo) {
        String mensagemErro = "";

        try {
            validarId(id);
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

        this.id = id;
        this.senha = senha;
        this.tipo = tipo;
    }

    private void validarId(Long id) {
        if (Objects.isNull(id))
            throw new BadArgumentException("O usuário deve possuir um ID.");
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
