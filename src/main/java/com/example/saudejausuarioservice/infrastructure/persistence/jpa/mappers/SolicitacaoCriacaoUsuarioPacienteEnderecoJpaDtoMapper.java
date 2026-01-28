package com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers;

import com.example.saudejausuarioservice.dtos.EnderecoUsuarioDto;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.SolicitacaoCriacaoUsuarioPacienteEnderecoJpa;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jpa")
public class SolicitacaoCriacaoUsuarioPacienteEnderecoJpaDtoMapper {
    public SolicitacaoCriacaoUsuarioPacienteEnderecoJpa toJpa(EnderecoUsuarioDto enderecoUsuarioDto) {
        return new SolicitacaoCriacaoUsuarioPacienteEnderecoJpa(enderecoUsuarioDto.id(), enderecoUsuarioDto.estado(), enderecoUsuarioDto.cidade(),
                enderecoUsuarioDto.bairro(), enderecoUsuarioDto.rua(), enderecoUsuarioDto.numero(), enderecoUsuarioDto.complemento(), enderecoUsuarioDto.cep());
    }

    public EnderecoUsuarioDto toDto(SolicitacaoCriacaoUsuarioPacienteEnderecoJpa solicitacaoCriacaoUsuarioPacienteEnderecoJpa) {
        return new EnderecoUsuarioDto(solicitacaoCriacaoUsuarioPacienteEnderecoJpa.getId(), solicitacaoCriacaoUsuarioPacienteEnderecoJpa.getEstado(),
                solicitacaoCriacaoUsuarioPacienteEnderecoJpa.getCidade(), solicitacaoCriacaoUsuarioPacienteEnderecoJpa.getBairro(),
                solicitacaoCriacaoUsuarioPacienteEnderecoJpa.getRua(), solicitacaoCriacaoUsuarioPacienteEnderecoJpa.getNumero(),
                solicitacaoCriacaoUsuarioPacienteEnderecoJpa.getComplemento(), solicitacaoCriacaoUsuarioPacienteEnderecoJpa.getCep());
    }
}
