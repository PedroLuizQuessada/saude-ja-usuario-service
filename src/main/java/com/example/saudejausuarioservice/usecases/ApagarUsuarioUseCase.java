package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.exceptions.NaoEncontradoException;
import com.example.saudejausuarioservice.gateways.FichaPacienteGateway;
import com.example.saudejausuarioservice.gateways.NotificacaoGateway;
import com.example.saudejausuarioservice.gateways.PostoSaudeGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import dtos.requests.EnviarNotificacaoRequest;
import enums.TipoUsuarioEnum;

import java.util.Objects;

public class ApagarUsuarioUseCase {

    private static final String ASSUNTO_NOTIFICACAO = "Usuário Saúde Já apagado.";
    private static final String MENSAGEM_NOTIFICACAO = "Seu usuário do Saúde Já foi apagado com sucesso.";
    private final UsuarioGateway usuarioGateway;
    private final FichaPacienteGateway fichaPacienteGateway;
    private final PostoSaudeGateway postoSaudeGateway;
    private final NotificacaoGateway notificacaoGateway;

    public ApagarUsuarioUseCase(UsuarioGateway usuarioGateway, FichaPacienteGateway fichaPacienteGateway, PostoSaudeGateway postoSaudeGateway, NotificacaoGateway notificacaoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.fichaPacienteGateway = fichaPacienteGateway;
        this.postoSaudeGateway = postoSaudeGateway;
        this.notificacaoGateway = notificacaoGateway;
    }

    public void executar(Long id) {
        Usuario usuario = usuarioGateway.getUsuarioById(id);
        usuarioGateway.deleteUsuarioById(usuario.getId());
        if (Objects.equals(usuario.getTipo(), TipoUsuarioEnum.PACIENTE)) {
            try {
                fichaPacienteGateway.apagarFichaPacienteByPacienteId(usuario.getId());
            } catch (NaoEncontradoException ignored) {
            }
            postoSaudeGateway.removerPaciente(usuario.getId());
        }
        else
            postoSaudeGateway.removerProfissionalSaude(usuario.getId());
        notificacaoGateway.enviarNotificacao(new EnviarNotificacaoRequest(usuario.getEmail(), ASSUNTO_NOTIFICACAO, MENSAGEM_NOTIFICACAO));
    }
}
