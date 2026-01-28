package com.example.saudejausuarioservice.entidades;

import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import enums.TipoUsuarioEnum;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public class Usuario {
    private static final Pattern regexCelular = Pattern.compile("^\\d{11}$");

    private final Long id;
    private String nome;
    private String email;
    private String senha;
    private final TipoUsuarioEnum tipo;
    private EnderecoUsuario enderecoUsuario;
    private String celular;

    public Usuario(Long id, String nome, String email, String senha, TipoUsuarioEnum tipo, EnderecoUsuario enderecoUsuario,
                   String celular, boolean senhaDescriptografada) {
        String mensagemErro = "";

        try {
            validarNome(nome);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarEmail(email);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarSenha(senha, senhaDescriptografada);
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

        try {
            validarEnderecoUsuario(enderecoUsuario);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarCelular(celular);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        if (!mensagemErro.isEmpty())
            throw new BadArgumentException(mensagemErro);

        this.id = id;
        this.nome = nome;
        this.email = email;
        if (senhaDescriptografada) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.senha = encoder.encode(senha);
        }
        else {
            this.senha = senha;
        }
        this.tipo = tipo;
        this.enderecoUsuario = enderecoUsuario;
        this.celular = celular;
    }

    public void setSenha(String senha, boolean senhaDescriptografada) {
        validarSenha(senha, senhaDescriptografada);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.senha = encoder.encode(senha);
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }

    public void setEnderecoUsuario(EnderecoUsuario enderecoUsuario) {
        validarEnderecoUsuario(enderecoUsuario);
        this.enderecoUsuario = enderecoUsuario;
    }

    public void setCelular(String celular) {
        validarCelular(celular);
        this.celular = celular;
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome) || nome.isEmpty())
            throw new BadArgumentException("O usuário deve possuir um nome.");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email) || email.isEmpty())
            throw new BadArgumentException("O usuário deve possuir um e-mail.");

        if (!EmailValidator.getInstance().isValid(email))
            throw new BadArgumentException("E-mail inválido.");
    }

    private void validarSenha(String senha, boolean senhaDescriptografada) {
        if (Objects.isNull(senha) || senha.isEmpty())
            throw new BadArgumentException("O usuário deve possuir uma senha.");

        if (senhaDescriptografada && senha.length() < 6)
            throw new BadArgumentException("A senha do usuário deve possuir ao menos 6 caracteres.");
    }

    private void validarTipo(TipoUsuarioEnum tipo) {
        if (Objects.isNull(tipo)) {
            throw new BadArgumentException("O usuário deve possuir um tipo de usuário.");
        }
    }

    private void validarEnderecoUsuario(EnderecoUsuario enderecoUsuario) {
        if (Objects.isNull(enderecoUsuario)) {
            throw new BadArgumentException("O usuário deve possuir um endereço.");
        }
    }

    private void validarCelular(String celular) {
        if (Objects.isNull(celular) || !regexCelular.matcher(celular).matches())
            throw new BadArgumentException("Celular inválido.");
    }
}
