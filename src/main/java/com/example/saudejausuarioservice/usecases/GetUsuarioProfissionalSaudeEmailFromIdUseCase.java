package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.UsuarioEmailPage;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import dtos.requests.ProfissionalSaudeIdPageRequest;

public class GetUsuarioProfissionalSaudeEmailFromIdUseCase {

    private final UsuarioGateway usuarioGateway;

    public GetUsuarioProfissionalSaudeEmailFromIdUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public UsuarioEmailPage executar(ProfissionalSaudeIdPageRequest profissionalSaudeIdPageRequest) {
        return usuarioGateway.getUsuarioProfissionalSaudeEmailFromId(profissionalSaudeIdPageRequest.getPage(), profissionalSaudeIdPageRequest.getSize(), profissionalSaudeIdPageRequest.getContent());
    }
}
