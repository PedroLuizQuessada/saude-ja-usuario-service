package com.example.saudejausuarioservice.infrastructure.persistence.jpa.models;

import com.example.saudejausuarioservice.infrastructure.exceptions.BadJpaArgumentException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.util.Objects;

@Entity
@Table(name = "solicitacoes_troca_senha_usuario")
@Getter
@NoArgsConstructor
@Profile("jpa")
public class SolicitacaoTrocaSenhaUsuarioJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nova_senha")
    private String novaSenha;

    @OneToOne(optional = false)
    @JoinColumn(unique = true, nullable = false, referencedColumnName = "id", name = "solicitacao_conta_usuario")
    private SolicitacaoContaUsuarioJpa solicitacaoContaUsuario;

    @OneToOne(optional = false)
    @JoinColumn(unique = true, nullable = false, referencedColumnName = "id")
    private UsuarioJpa usuario;

    public SolicitacaoTrocaSenhaUsuarioJpa(Long id, String novaSenha, SolicitacaoContaUsuarioJpa solicitacaoContaUsuario, UsuarioJpa usuario) {
        String message = "";

        try {
            validarNovaSenha(novaSenha);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarSolicitacaoConta(solicitacaoContaUsuario);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarUsuario(usuario);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        if (!message.isEmpty())
            throw new BadJpaArgumentException(message);

        this.id = id;
        this.novaSenha = novaSenha;
        this.solicitacaoContaUsuario = solicitacaoContaUsuario;
        this.usuario = usuario;
    }

    private void validarNovaSenha(String senha) {
        if (Objects.isNull(senha))
            throw new BadJpaArgumentException("A solicitação de troca de senha do usuário deve possuir uma nova senha para ser armazenada no banco de dados.");

        if (senha.length() > 255)
            throw new BadJpaArgumentException("Falha ao gerar senha criptografada do usuário, favor contactar o administrador.");
    }

    private void validarSolicitacaoConta(SolicitacaoContaUsuarioJpa solicitacaoContaUsuario) {
        if (Objects.isNull(solicitacaoContaUsuario))
            throw new BadJpaArgumentException("A solicitação de troca de senha do usuário deve possuir uma solicitação de conta para ser armazenada no banco de dados.");
    }

    private void validarUsuario(UsuarioJpa usuario) {
        if (Objects.isNull(usuario))
            throw new BadJpaArgumentException("A solicitação de troca de senha do usuário deve possuir um usuario para ser armazenada no banco de dados.");
    }
}
