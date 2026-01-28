package com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers;

import com.example.saudejausuarioservice.dtos.EnderecoUsuarioDto;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.EnderecoUsuarioJpa;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jpa")
public class EnderecoUsuarioJpaDtoMapper {
    public EnderecoUsuarioJpa toJpa(EnderecoUsuarioDto enderecoUsuarioDto) {
        return new EnderecoUsuarioJpa(enderecoUsuarioDto.id(), enderecoUsuarioDto.estado(), enderecoUsuarioDto.cidade(),
                enderecoUsuarioDto.bairro(), enderecoUsuarioDto.rua(), enderecoUsuarioDto.numero(), enderecoUsuarioDto.complemento(),
                enderecoUsuarioDto.cep());
    }

    public EnderecoUsuarioDto toDto(EnderecoUsuarioJpa enderecoUsuarioJpa) {
        return new EnderecoUsuarioDto(enderecoUsuarioJpa.getId(), enderecoUsuarioJpa.getEstado(), enderecoUsuarioJpa.getCidade(),
                enderecoUsuarioJpa.getBairro(), enderecoUsuarioJpa.getRua(), enderecoUsuarioJpa.getNumero(),
                enderecoUsuarioJpa.getComplemento(), enderecoUsuarioJpa.getCep());
    }
}
