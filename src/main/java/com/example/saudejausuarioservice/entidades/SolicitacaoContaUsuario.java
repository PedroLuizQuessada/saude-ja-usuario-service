package com.example.saudejausuarioservice.entidades;

import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Getter
public class SolicitacaoContaUsuario {
    private final Long id;
    private final String codigo;
    private final LocalDateTime validade;
    @Setter
    private boolean consumida;

    public SolicitacaoContaUsuario() {
        Random rand = new Random();
        this.id = null;
        this.codigo = 100000 + rand.nextInt(900000) + "";
        this.validade = LocalDateTime.now().plusMinutes(30);
        this.consumida = false;
    }

    public SolicitacaoContaUsuario(Long id, String codigo, LocalDateTime validade, boolean consumida) {
        String mensagemErro = "";

        try {
            validarCodigo(codigo);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarValidade(validade);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        if (!mensagemErro.isEmpty())
            throw new BadArgumentException(mensagemErro);

        this.id = id;
        this.codigo = codigo;
        this.validade = validade;
        this.consumida = consumida;
    }

    private void validarCodigo(String codigo) {
        if (Objects.isNull(codigo))
            throw new BadArgumentException("A solicitação de conta do usuário deve possuir um código.");

        if (codigo.length() != 6)
            throw new BadArgumentException("Código inválido.");
    }

    private void validarValidade(LocalDateTime validade) {
        if (Objects.isNull(validade))
            throw new BadArgumentException(("A solicitação de conta do usuário deve possuir uma data de validade."));
        if (validade.isBefore(LocalDateTime.now()))
            throw new BadArgumentException(("A validade da solicitação de conta do usuário deve ser uma data futura."));
    }
}
