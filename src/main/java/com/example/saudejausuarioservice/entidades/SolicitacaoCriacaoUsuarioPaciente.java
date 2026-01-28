package com.example.saudejausuarioservice.entidades;

import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

@Getter
public class SolicitacaoCriacaoUsuarioPaciente {
    private final Long id;
    private final SolicitacaoContaUsuario solicitacaoContaUsuario;
    private final String nome;
    private final String email;
    private final String senha;
    private final SolicitacaoCriacaoUsuarioPacienteEndereco solicitacaoEnderecoUsuario;
    private final String celular;

    public SolicitacaoCriacaoUsuarioPaciente(Long id, SolicitacaoContaUsuario solicitacaoContaUsuario, String nome, String email, String senha, SolicitacaoCriacaoUsuarioPacienteEndereco solicitacaoEnderecoUsuario, String celular) {
        String mensagemErro = "";

        try {
            validarSolicitacaoContaUsuario(solicitacaoContaUsuario);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarSolicitacaoEnderecoUsuario(solicitacaoEnderecoUsuario);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        if (!mensagemErro.isEmpty())
            throw new BadArgumentException(mensagemErro);

        this.id = id;
        this.solicitacaoContaUsuario = solicitacaoContaUsuario;
        this.nome = nome;
        this.email = email;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.senha = encoder.encode(senha);
        this.solicitacaoEnderecoUsuario = solicitacaoEnderecoUsuario;
        this.celular = celular;
    }

    private void validarSolicitacaoContaUsuario(SolicitacaoContaUsuario solicitacaoContaUsuario) {
        if (Objects.isNull(solicitacaoContaUsuario))
            throw new BadArgumentException("A solicitação de criação do usuário paciente deve possuir uma solicitação de conta.");
    }

    private void validarSolicitacaoEnderecoUsuario(SolicitacaoCriacaoUsuarioPacienteEndereco solicitacaoEnderecoUsuario) {
        if (Objects.isNull(solicitacaoEnderecoUsuario))
            throw new BadArgumentException("A solicitação de criação do usuário paciente deve possuir um endereço.");
    }
}
