package com.example.saudejausuarioservice.mappers;

import com.example.saudejausuarioservice.entidades.CredenciaisUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import dtos.responses.CredenciaisUsuarioResponse;

public class CredenciaisUsuarioMapper {
    private CredenciaisUsuarioMapper() {}

    public static CredenciaisUsuario toEntidade(Usuario usuario) {
        return new CredenciaisUsuario(usuario.getId(), usuario.getSenha(), usuario.getTipo());
    }

    public static CredenciaisUsuarioResponse toResponse(CredenciaisUsuario credenciaisUsuario) {
        return new CredenciaisUsuarioResponse(credenciaisUsuario.getId(), credenciaisUsuario.getSenha(), credenciaisUsuario.getTipo());
    }
}
