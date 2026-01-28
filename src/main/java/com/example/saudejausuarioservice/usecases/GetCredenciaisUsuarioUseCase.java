package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.CredenciaisUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.CredenciaisUsuarioMapper;

public class GetCredenciaisUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    public GetCredenciaisUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public CredenciaisUsuario executar(String email) {
        Usuario usuario = usuarioGateway.getUsuarioByEmail(email);
        return CredenciaisUsuarioMapper.toEntidade(usuario);
    }
}
