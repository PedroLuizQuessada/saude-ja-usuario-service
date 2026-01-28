package com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers;

import com.example.saudejausuarioservice.dtos.SolicitacaoCriacaoUsuarioPacienteDto;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.SolicitacaoCriacaoUsuarioPacienteJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Profile("jpa")
public class SolicitacaoCriacaoUsuarioPacienteJpaDtoMapper {

    @Autowired
    private SolicitacaoContaUsuarioJpaDtoMapper solicitacaoContaUsuarioJpaDtoMapper;

    @Autowired
    private SolicitacaoCriacaoUsuarioPacienteEnderecoJpaDtoMapper solicitacaoCriacaoUsuarioPacienteEnderecoJpaDtoMapper;

    public SolicitacaoCriacaoUsuarioPacienteJpa toJpa(SolicitacaoCriacaoUsuarioPacienteDto solicitacaoCriacaoUsuarioPacienteDto) {
        return new SolicitacaoCriacaoUsuarioPacienteJpa(solicitacaoCriacaoUsuarioPacienteDto.id(),
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteDto.solicitacaoContaUsuarioDto()) ? null :
                        solicitacaoContaUsuarioJpaDtoMapper.toJpa(solicitacaoCriacaoUsuarioPacienteDto.solicitacaoContaUsuarioDto()),
                solicitacaoCriacaoUsuarioPacienteDto.nome(), solicitacaoCriacaoUsuarioPacienteDto.email(), solicitacaoCriacaoUsuarioPacienteDto.senha(),
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteDto.enderecoUsuario()) ? null :
                        solicitacaoCriacaoUsuarioPacienteEnderecoJpaDtoMapper.toJpa(solicitacaoCriacaoUsuarioPacienteDto.enderecoUsuario()),
                solicitacaoCriacaoUsuarioPacienteDto.celular());
    }

    public SolicitacaoCriacaoUsuarioPacienteDto toDto(SolicitacaoCriacaoUsuarioPacienteJpa solicitacaoCriacaoUsuarioPacienteJpa) {
        return new SolicitacaoCriacaoUsuarioPacienteDto(solicitacaoCriacaoUsuarioPacienteJpa.getId(),
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteJpa.getSolicitacaoContaUsuario()) ? null :
                        solicitacaoContaUsuarioJpaDtoMapper.toDto(solicitacaoCriacaoUsuarioPacienteJpa.getSolicitacaoContaUsuario()),
                solicitacaoCriacaoUsuarioPacienteJpa.getNome(), solicitacaoCriacaoUsuarioPacienteJpa.getEmail(), solicitacaoCriacaoUsuarioPacienteJpa.getSenha(),
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteJpa.getSolicitacaoCriacaoUsuarioPacienteEndereco()) ? null :
                        solicitacaoCriacaoUsuarioPacienteEnderecoJpaDtoMapper.toDto(solicitacaoCriacaoUsuarioPacienteJpa.getSolicitacaoCriacaoUsuarioPacienteEndereco()),
                solicitacaoCriacaoUsuarioPacienteJpa.getCelular());
    }
}
