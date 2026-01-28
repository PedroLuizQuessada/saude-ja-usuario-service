package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.TokenGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;

public class ApagarProprioUsuarioUseCase {
    private final TokenGateway tokenGateway;
    private final UsuarioGateway usuarioGateway;

    public ApagarProprioUsuarioUseCase(TokenGateway tokenGateway, UsuarioGateway usuarioGateway) {
        this.tokenGateway = tokenGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public void executar(String token) {
        String email = tokenGateway.getEmail(token);
        Usuario usuario = usuarioGateway.getUsuarioByEmail(email);
        usuarioGateway.deleteUsuarioById(usuario.getId());
    }
}
