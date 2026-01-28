package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;

public class ApagarUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    public ApagarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void executar(Long id) {
        Usuario usuario = usuarioGateway.getUsuarioById(id);
        usuarioGateway.deleteUsuarioById(usuario.getId());
    }
}
