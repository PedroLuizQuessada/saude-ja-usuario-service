package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.UsuarioEmailPage;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import dtos.requests.PacienteIdPageRequest;

public class GetUsuarioPacienteEmailFromIdUseCase {

    private final UsuarioGateway usuarioGateway;

    public GetUsuarioPacienteEmailFromIdUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public UsuarioEmailPage executar(PacienteIdPageRequest pacienteIdPageRequest) {
        return usuarioGateway.getUsuarioPacienteEmailFromId(pacienteIdPageRequest.getPage(), pacienteIdPageRequest.getSize(), pacienteIdPageRequest.getContent());
    }
}
