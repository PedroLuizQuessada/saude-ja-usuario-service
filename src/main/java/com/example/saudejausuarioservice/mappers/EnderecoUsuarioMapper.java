package com.example.saudejausuarioservice.mappers;

import com.example.saudejausuarioservice.dtos.EnderecoUsuarioDto;
import com.example.saudejausuarioservice.entidades.EnderecoUsuario;
import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPacienteEndereco;
import dtos.requests.EnderecoUsuarioRequest;
import dtos.responses.EnderecoUsuarioResponse;

public class EnderecoUsuarioMapper {
    private EnderecoUsuarioMapper() {}

    public static EnderecoUsuario toEntidade(EnderecoUsuarioRequest enderecoUsuarioRequest) {
        return new EnderecoUsuario(null, enderecoUsuarioRequest.estado(), enderecoUsuarioRequest.cidade(), enderecoUsuarioRequest.bairro(),
                enderecoUsuarioRequest.rua(), enderecoUsuarioRequest.numero(), enderecoUsuarioRequest.complemento(), enderecoUsuarioRequest.cep());
    }

    public static EnderecoUsuario toEntidade(EnderecoUsuarioDto enderecoUsuarioDto) {
        return new EnderecoUsuario(enderecoUsuarioDto.id(), enderecoUsuarioDto.estado(), enderecoUsuarioDto.cidade(), enderecoUsuarioDto.bairro(),
                enderecoUsuarioDto.rua(), enderecoUsuarioDto.numero(), enderecoUsuarioDto.complemento(), enderecoUsuarioDto.cep());
    }

    public static EnderecoUsuario toEntidade(SolicitacaoCriacaoUsuarioPacienteEndereco solicitacaoCriacaoUsuarioPacienteEndereco) {
        return new EnderecoUsuario(solicitacaoCriacaoUsuarioPacienteEndereco.getId(), solicitacaoCriacaoUsuarioPacienteEndereco.getEstado(), solicitacaoCriacaoUsuarioPacienteEndereco.getCidade(),
                solicitacaoCriacaoUsuarioPacienteEndereco.getBairro(), solicitacaoCriacaoUsuarioPacienteEndereco.getRua(), solicitacaoCriacaoUsuarioPacienteEndereco.getNumero(), solicitacaoCriacaoUsuarioPacienteEndereco.getComplemento(),
                solicitacaoCriacaoUsuarioPacienteEndereco.getCep());
    }

    public static EnderecoUsuarioDto toDto(EnderecoUsuario enderecoUsuario) {
        return new EnderecoUsuarioDto(enderecoUsuario.getId(), enderecoUsuario.getEstado(), enderecoUsuario.getCidade(),
                enderecoUsuario.getBairro(), enderecoUsuario.getRua(), enderecoUsuario.getNumero(), enderecoUsuario.getComplemento(),
                enderecoUsuario.getCep());
    }

    public static EnderecoUsuarioResponse toResponse(EnderecoUsuario enderecoUsuario) {
        return new EnderecoUsuarioResponse(enderecoUsuario.getId(), enderecoUsuario.getEstado(), enderecoUsuario.getCidade(),
                enderecoUsuario.getBairro(), enderecoUsuario.getRua(), enderecoUsuario.getNumero(), enderecoUsuario.getComplemento(),
                enderecoUsuario.getCep());
    }
}
