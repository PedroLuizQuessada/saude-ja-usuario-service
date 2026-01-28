package com.example.saudejausuarioservice.infrastructure.persistence.jpa.models;

import com.example.saudejausuarioservice.infrastructure.exceptions.BadJpaArgumentException;
import enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.util.Objects;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@Profile("jpa")
public class UsuarioJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuarioEnum tipo;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true, nullable = false, referencedColumnName = "id", name = "endereco_usuario")
    private EnderecoUsuarioJpa enderecoUsuario;

    @Column(nullable = false)
    private String celular;

    public UsuarioJpa(Long id, String nome, String email, String senha, TipoUsuarioEnum tipo, EnderecoUsuarioJpa enderecoUsuario, String celular) {
        String message = "";

        try {
            validarNome(nome);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarEmail(email);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarSenha(senha);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarTipo(tipo);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarEnderecoUsuario(enderecoUsuario);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarCelular(celular);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        if (!message.isEmpty())
            throw new BadJpaArgumentException(message);

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.enderecoUsuario = enderecoUsuario;
        this.celular = celular;
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome))
            throw new BadJpaArgumentException("O usuário deve possuir um nome para ser armazenado no banco de dados.");

        if (nome.length() > 255)
            throw new BadJpaArgumentException("O nome do usuário deve possuir até 255 caracteres para ser armazenado no banco de dados.");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email))
            throw new BadJpaArgumentException("O usuário deve possuir um e-mail para ser armazenado no banco de dados.");

        if (email.length() > 45)
            throw new BadJpaArgumentException("O e-mail do usuário deve possuir até 45 caracteres para ser armazenado no banco de dados.");
    }

    private void validarSenha(String senha) {
        if (Objects.isNull(senha))
            throw new BadJpaArgumentException("O usuário deve possuir uma senha para ser armazenado no banco de dados.");

        if (senha.length() > 255)
            throw new BadJpaArgumentException("Falha ao gerar senha criptografada do usuário, favor contactar o administrador.");
    }

    private void validarTipo(TipoUsuarioEnum tipo) {
        if (Objects.isNull(tipo))
            throw new BadJpaArgumentException("O usuário deve possuir tipo para ser armazenado no banco de dados.");
    }

    private void validarEnderecoUsuario(EnderecoUsuarioJpa enderecoUsuario) {
        if (Objects.isNull(enderecoUsuario))
            throw new BadJpaArgumentException("O usuário deve possuir endereço para ser armazenado no banco de dados.");
    }

    private void validarCelular(String celular) {
        if (Objects.isNull(celular))
            throw new BadJpaArgumentException("O usuário deve possuir celular para ser armazenado no banco de dados.");

        if (celular.length() > 255)
            throw new BadJpaArgumentException("O celular do usuário deve possuir até 255 caracteres para ser armazenado no banco de dados.");
    }
}
