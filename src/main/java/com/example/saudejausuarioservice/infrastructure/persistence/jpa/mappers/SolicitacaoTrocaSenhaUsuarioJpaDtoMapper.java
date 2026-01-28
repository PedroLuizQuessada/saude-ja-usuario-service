package com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers;

import com.example.saudejausuarioservice.dtos.SolicitacaoTrocaSenhaUsuarioDto;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.SolicitacaoTrocaSenhaUsuarioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Profile("jpa")
public class SolicitacaoTrocaSenhaUsuarioJpaDtoMapper {

    @Autowired
    private SolicitacaoContaUsuarioJpaDtoMapper solicitacaoContaUsuarioJpaDtoMapper;

    @Autowired
    private UsuarioJpaDtoMapper usuarioJpaDtoMapper;

    public SolicitacaoTrocaSenhaUsuarioJpa toJpa(SolicitacaoTrocaSenhaUsuarioDto solicitacaoTrocaSenhaUsuarioDto) {
        return new SolicitacaoTrocaSenhaUsuarioJpa(solicitacaoTrocaSenhaUsuarioDto.id(), solicitacaoTrocaSenhaUsuarioDto.novaSenha(),
                Objects.isNull(solicitacaoTrocaSenhaUsuarioDto.solicitacaoContaUsuarioDto()) ? null :
                        solicitacaoContaUsuarioJpaDtoMapper.toJpa(solicitacaoTrocaSenhaUsuarioDto.solicitacaoContaUsuarioDto()),
                Objects.isNull(solicitacaoTrocaSenhaUsuarioDto.usuarioDto()) ? null :
                        usuarioJpaDtoMapper.toJpa(solicitacaoTrocaSenhaUsuarioDto.usuarioDto()));
    }

    public SolicitacaoTrocaSenhaUsuarioDto toDto(SolicitacaoTrocaSenhaUsuarioJpa solicitacaoTrocaSenhaUsuarioJpa) {
        return new SolicitacaoTrocaSenhaUsuarioDto(solicitacaoTrocaSenhaUsuarioJpa.getId(), solicitacaoTrocaSenhaUsuarioJpa.getNovaSenha(),
                Objects.isNull(solicitacaoTrocaSenhaUsuarioJpa.getSolicitacaoContaUsuario()) ? null :
                        solicitacaoContaUsuarioJpaDtoMapper.toDto(solicitacaoTrocaSenhaUsuarioJpa.getSolicitacaoContaUsuario()),
                Objects.isNull(solicitacaoTrocaSenhaUsuarioJpa.getUsuario()) ? null :
                        usuarioJpaDtoMapper.toDto(solicitacaoTrocaSenhaUsuarioJpa.getUsuario()));
    }
}
