package com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers;

import com.example.saudejausuarioservice.dtos.SolicitacaoContaUsuarioDto;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.SolicitacaoContaUsuarioJpa;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jpa")
public class SolicitacaoContaUsuarioJpaDtoMapper {

    public SolicitacaoContaUsuarioJpa toJpa(SolicitacaoContaUsuarioDto solicitacaoContaUsuarioDto) {
        return new SolicitacaoContaUsuarioJpa(solicitacaoContaUsuarioDto.id(), solicitacaoContaUsuarioDto.codigo(),
                solicitacaoContaUsuarioDto.validade(), solicitacaoContaUsuarioDto.consumida());
    }

    public SolicitacaoContaUsuarioDto toDto(SolicitacaoContaUsuarioJpa solicitacaoContaUsuarioJpa) {
        return new SolicitacaoContaUsuarioDto(solicitacaoContaUsuarioJpa.getId(), solicitacaoContaUsuarioJpa.getCodigo(),
                solicitacaoContaUsuarioJpa.getValidade(), solicitacaoContaUsuarioJpa.getConsumida());
    }
}
