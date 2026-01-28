package com.example.saudejausuarioservice.infrastructure.persistence.jpa.models;

import com.example.saudejausuarioservice.infrastructure.exceptions.BadJpaArgumentException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.util.Objects;

@Entity
@Table(name = "solicitacoes_criacao_usuario_paciente")
@Getter
@NoArgsConstructor
@Profile("jpa")
public class SolicitacaoCriacaoUsuarioPacienteJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(unique = true, nullable = false, referencedColumnName = "id", name = "solicitacao_conta_usuario")
    private SolicitacaoContaUsuarioJpa solicitacaoContaUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToOne(optional = false)
    @JoinColumn(unique = true, nullable = false, referencedColumnName = "id", name = "solicitacao_criacao_usuario_paciente_endereco")
    private SolicitacaoCriacaoUsuarioPacienteEnderecoJpa solicitacaoCriacaoUsuarioPacienteEndereco;

    @Column(nullable = false)
    private String celular;

    public SolicitacaoCriacaoUsuarioPacienteJpa(Long id, SolicitacaoContaUsuarioJpa solicitacaoContaUsuario, String nome, String email, String senha, SolicitacaoCriacaoUsuarioPacienteEnderecoJpa solicitacaoCriacaoUsuarioPacienteEndereco, String celular) {
        String message = "";

        try {
            validarSolicitacaoConta(solicitacaoContaUsuario);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

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
            validarEnderecoUsuario(solicitacaoCriacaoUsuarioPacienteEndereco);
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
        this.solicitacaoContaUsuario = solicitacaoContaUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.solicitacaoCriacaoUsuarioPacienteEndereco = solicitacaoCriacaoUsuarioPacienteEndereco;
        this.celular = celular;
    }

    private void validarSolicitacaoConta(SolicitacaoContaUsuarioJpa solicitacaoContaUsuario) {
        if (Objects.isNull(solicitacaoContaUsuario))
            throw new BadJpaArgumentException("A solicitação de criação de usuário paciente deve possuir uma solicitação de conta para ser armazenada no banco de dados.");
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome))
            throw new BadJpaArgumentException("A solicitação de criação de usuário paciente deve possuir um nome para ser armazenada no banco de dados.");

        if (nome.length() > 255)
            throw new BadJpaArgumentException("O nome da solicitação de criação de usuário paciente deve possuir até 255 caracteres para ser armazenada no banco de dados.");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email))
            throw new BadJpaArgumentException("A solicitação de criação de usuário paciente deve possuir um e-mail para ser armazenada no banco de dados.");

        if (email.length() > 45)
            throw new BadJpaArgumentException("O e-mail da solicitação de criação de usuário paciente deve possuir até 45 caracteres para ser armazenada no banco de dados.");
    }

    private void validarSenha(String senha) {
        if (Objects.isNull(senha))
            throw new BadJpaArgumentException("A solicitação de criação de usuário paciente deve possuir uma senha para ser armazenada no banco de dados.");

        if (senha.length() > 255)
            throw new BadJpaArgumentException("Falha ao gerar senha criptografada do usuário, favor contactar o administrador.");
    }

    private void validarEnderecoUsuario(SolicitacaoCriacaoUsuarioPacienteEnderecoJpa solicitacaoCriacaoUsuarioPacienteEndereco) {
        if (Objects.isNull(solicitacaoCriacaoUsuarioPacienteEndereco))
            throw new BadJpaArgumentException("A solicitação de criação de usuário paciente deve possuir endereço para ser armazenada no banco de dados.");
    }

    private void validarCelular(String celular) {
        if (Objects.isNull(celular))
            throw new BadJpaArgumentException("A solicitação de criação de usuário paciente deve possuir celular para ser armazenada no banco de dados.");

        if (celular.length() > 255)
            throw new BadJpaArgumentException("O celular da solicitação de criação de usuário paciente deve possuir até 255 caracteres para ser armazenado no banco de dados.");
    }
}
