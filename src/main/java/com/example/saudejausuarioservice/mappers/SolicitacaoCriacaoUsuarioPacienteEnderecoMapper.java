package com.example.saudejausuarioservice.mappers;

import com.example.saudejausuarioservice.dtos.EnderecoUsuarioDto;
import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPacienteEndereco;
import dtos.requests.EnderecoUsuarioRequest;
import dtos.responses.EnderecoUsuarioResponse;

public class SolicitacaoCriacaoUsuarioPacienteEnderecoMapper {
    private SolicitacaoCriacaoUsuarioPacienteEnderecoMapper() {}

    public static SolicitacaoCriacaoUsuarioPacienteEndereco toEntidade(EnderecoUsuarioRequest enderecoUsuarioRequest) {
        return new SolicitacaoCriacaoUsuarioPacienteEndereco(null, enderecoUsuarioRequest.estado(), enderecoUsuarioRequest.cidade(),
                enderecoUsuarioRequest.bairro(), enderecoUsuarioRequest.rua(), enderecoUsuarioRequest.numero(),
                enderecoUsuarioRequest.complemento(), enderecoUsuarioRequest.cep());
    }

    public static SolicitacaoCriacaoUsuarioPacienteEndereco toEntidade(EnderecoUsuarioDto enderecoUsuarioDto) {
        return new SolicitacaoCriacaoUsuarioPacienteEndereco(enderecoUsuarioDto.id(), enderecoUsuarioDto.estado(),
                enderecoUsuarioDto.cidade(), enderecoUsuarioDto.bairro(), enderecoUsuarioDto.rua(), enderecoUsuarioDto.numero(),
                enderecoUsuarioDto.complemento(), enderecoUsuarioDto.cep());
    }

    public static EnderecoUsuarioDto toDto(SolicitacaoCriacaoUsuarioPacienteEndereco solicitacaoCriacaoUsuarioPacienteEndereco) {
        return new EnderecoUsuarioDto(solicitacaoCriacaoUsuarioPacienteEndereco.getId(), solicitacaoCriacaoUsuarioPacienteEndereco.getEstado(),
                solicitacaoCriacaoUsuarioPacienteEndereco.getCidade(), solicitacaoCriacaoUsuarioPacienteEndereco.getBairro(),
                solicitacaoCriacaoUsuarioPacienteEndereco.getRua(), solicitacaoCriacaoUsuarioPacienteEndereco.getNumero(),
                solicitacaoCriacaoUsuarioPacienteEndereco.getComplemento(), solicitacaoCriacaoUsuarioPacienteEndereco.getCep());
    }

    public static EnderecoUsuarioResponse toResponse(SolicitacaoCriacaoUsuarioPacienteEndereco solicitacaoCriacaoUsuarioPacienteEndereco) {
        return new EnderecoUsuarioResponse(solicitacaoCriacaoUsuarioPacienteEndereco.getId(), solicitacaoCriacaoUsuarioPacienteEndereco.getEstado(),
                solicitacaoCriacaoUsuarioPacienteEndereco.getCidade(), solicitacaoCriacaoUsuarioPacienteEndereco.getBairro(),
                solicitacaoCriacaoUsuarioPacienteEndereco.getRua(), solicitacaoCriacaoUsuarioPacienteEndereco.getNumero(),
                solicitacaoCriacaoUsuarioPacienteEndereco.getComplemento(), solicitacaoCriacaoUsuarioPacienteEndereco.getCep());
    }
}
