package com.example.saudejausuarioservice.entidades;

import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SolicitacaoTrocaSenhaUsuario {
    private final Long id;
    private final String novaSenha;
    private final SolicitacaoContaUsuario solicitacaoContaUsuario;
    private final Usuario usuario;

    public SolicitacaoTrocaSenhaUsuario(Long id, String novaSenha, SolicitacaoContaUsuario solicitacaoContaUsuario, Usuario usuario) {
        String mensagemErro = "";

        try {
            validarSolicitacaoContaUsuario(solicitacaoContaUsuario);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarUsuario(usuario);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        if (!mensagemErro.isEmpty())
            throw new BadArgumentException(mensagemErro);

        this.id = id;
        this.novaSenha = novaSenha;
        this.solicitacaoContaUsuario = solicitacaoContaUsuario;
        this.usuario = usuario;
    }

    private void validarSolicitacaoContaUsuario(SolicitacaoContaUsuario solicitacaoContaUsuario) {
        if (Objects.isNull(solicitacaoContaUsuario))
            throw new BadArgumentException("A solicitação de troca de senha do usuário deve possuir uma solicitação de conta.");
    }

    private void validarUsuario(Usuario usuario) {
        if (Objects.isNull(usuario))
            throw new BadArgumentException("A solicitação de troca de senha do usuário deve possuir um usuário.");
    }
}
