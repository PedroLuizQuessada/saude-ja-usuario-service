package com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers;

import com.example.saudejausuarioservice.dtos.UsuarioDto;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.UsuarioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Profile("jpa")
public class UsuarioJpaDtoMapper {

    @Autowired
    private EnderecoUsuarioJpaDtoMapper enderecoUsuarioJpaDtoMapper;

    public UsuarioJpa toJpa(UsuarioDto usuarioDto) {
        return new UsuarioJpa(usuarioDto.id(), usuarioDto.nome(), usuarioDto.email(), usuarioDto.senha(), usuarioDto.tipo(),
                Objects.isNull(usuarioDto.enderecoUsuario()) ? null : enderecoUsuarioJpaDtoMapper.toJpa(usuarioDto.enderecoUsuario()),
                usuarioDto.celular());
    }

    public UsuarioDto toDto(UsuarioJpa usuarioJpa) {
        return new UsuarioDto(usuarioJpa.getId(), usuarioJpa.getNome(), usuarioJpa.getEmail(), usuarioJpa.getSenha(), usuarioJpa.getTipo(),
                Objects.isNull(usuarioJpa.getEnderecoUsuario()) ? null : enderecoUsuarioJpaDtoMapper.toDto(usuarioJpa.getEnderecoUsuario()),
                usuarioJpa.getCelular());
    }
}
