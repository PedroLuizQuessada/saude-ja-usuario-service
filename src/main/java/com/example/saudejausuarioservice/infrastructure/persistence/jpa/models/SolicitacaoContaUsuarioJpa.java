package com.example.saudejausuarioservice.infrastructure.persistence.jpa.models;

import com.example.saudejausuarioservice.infrastructure.exceptions.BadJpaArgumentException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "solicitacoes_conta_usuario")
@Getter
@NoArgsConstructor
@Profile("jpa")
public class SolicitacaoContaUsuarioJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private LocalDateTime validade;

    @Column(nullable = false)
    private Boolean consumida;

    public SolicitacaoContaUsuarioJpa(Long id, String codigo, LocalDateTime validade, Boolean consumida) {
        String message = "";

        try {
            validarCodigo(codigo);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarValidade(validade);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        try {
            validarConsumida(consumida);
        }
        catch (RuntimeException e) {
            message = message + " " + e.getMessage();
        }

        if (!message.isEmpty())
            throw new BadJpaArgumentException(message);

        this.id = id;
        this.codigo = codigo;
        this.validade = validade;
        this.consumida = consumida;
    }

    private void validarCodigo(String codigo) {
        if (Objects.isNull(codigo))
            throw new BadJpaArgumentException("A solicitação de conta do usuário deve possuir um código para ser armazenado no banco de dados.");
    }

    private void validarValidade(LocalDateTime validade) {
        if (Objects.isNull(validade))
            throw new BadJpaArgumentException("A solicitação de conta do usuário deve possuir uma validade para ser armazenada no banco de dados.");
    }

    private void validarConsumida(Boolean consumida) {
        if (Objects.isNull(consumida))
            throw new BadJpaArgumentException("A solicitação de conta do usuário deve possuir um indicativo de se foi consumida para ser armazenada no banco de dados.");
    }
}
