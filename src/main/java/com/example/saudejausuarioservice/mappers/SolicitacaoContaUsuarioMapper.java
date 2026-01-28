package com.example.saudejausuarioservice.mappers;

import com.example.saudejausuarioservice.dtos.SolicitacaoContaUsuarioDto;
import com.example.saudejausuarioservice.dtos.SolicitacaoCriacaoUsuarioPacienteDto;
import com.example.saudejausuarioservice.dtos.SolicitacaoTrocaSenhaUsuarioDto;
import com.example.saudejausuarioservice.entidades.SolicitacaoContaUsuario;
import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPaciente;
import com.example.saudejausuarioservice.entidades.SolicitacaoTrocaSenhaUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import dtos.requests.SolicitacaoCriacaoUsuarioPacienteRequest;
import dtos.responses.SolicitacaoContaUsuarioResponse;
import dtos.responses.SolicitacaoCriacaoUsuarioPacienteResponse;
import dtos.responses.SolicitacaoTrocaSenhaUsuarioResponse;

import java.util.Objects;

public class SolicitacaoContaUsuarioMapper {
    private SolicitacaoContaUsuarioMapper() {}

    public static SolicitacaoCriacaoUsuarioPaciente toEntidade(SolicitacaoCriacaoUsuarioPacienteRequest solicitacaoCriacaoUsuarioPacienteRequest,
                                                               SolicitacaoContaUsuario solicitacaoContaUsuario) {
        return new SolicitacaoCriacaoUsuarioPaciente(null, solicitacaoContaUsuario, solicitacaoCriacaoUsuarioPacienteRequest.nome(),
                solicitacaoCriacaoUsuarioPacienteRequest.email(), solicitacaoCriacaoUsuarioPacienteRequest.senha(),
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteRequest.enderecoUsuario()) ? null :
                        SolicitacaoCriacaoUsuarioPacienteEnderecoMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteRequest.enderecoUsuario()),
                solicitacaoCriacaoUsuarioPacienteRequest.celular());
    }

    public static SolicitacaoCriacaoUsuarioPaciente toEntidade(SolicitacaoCriacaoUsuarioPacienteDto solicitacaoCriacaoUsuarioPacienteDto) {
        return new SolicitacaoCriacaoUsuarioPaciente(solicitacaoCriacaoUsuarioPacienteDto.id(),
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteDto.solicitacaoContaUsuarioDto()) ? null : toEntidade(solicitacaoCriacaoUsuarioPacienteDto.solicitacaoContaUsuarioDto()),
                solicitacaoCriacaoUsuarioPacienteDto.nome(), solicitacaoCriacaoUsuarioPacienteDto.email(), solicitacaoCriacaoUsuarioPacienteDto.senha(),
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteDto.enderecoUsuario()) ? null :
                        SolicitacaoCriacaoUsuarioPacienteEnderecoMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteDto.enderecoUsuario()),
                solicitacaoCriacaoUsuarioPacienteDto.celular());
    }

    public static SolicitacaoTrocaSenhaUsuario toEntidade(SolicitacaoContaUsuario solicitacaoContaUsuario, Usuario usuario) {
        return new SolicitacaoTrocaSenhaUsuario(null, usuario.getSenha(), solicitacaoContaUsuario,
                usuario);
    }

    public static SolicitacaoTrocaSenhaUsuario toEntidade(SolicitacaoTrocaSenhaUsuarioDto solicitacaoTrocaSenhaUsuarioDto) {
        return new SolicitacaoTrocaSenhaUsuario(solicitacaoTrocaSenhaUsuarioDto.id(), solicitacaoTrocaSenhaUsuarioDto.novaSenha(),
                Objects.isNull(solicitacaoTrocaSenhaUsuarioDto.solicitacaoContaUsuarioDto()) ? null : toEntidade(solicitacaoTrocaSenhaUsuarioDto.solicitacaoContaUsuarioDto()),
                Objects.isNull(solicitacaoTrocaSenhaUsuarioDto.usuarioDto()) ? null : UsuarioMapper.toEntidade(solicitacaoTrocaSenhaUsuarioDto.usuarioDto()));
    }

    public static SolicitacaoCriacaoUsuarioPacienteDto toDto(SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPaciente) {
        return new  SolicitacaoCriacaoUsuarioPacienteDto(solicitacaoCriacaoUsuarioPaciente.getId(),
                Objects.isNull(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoContaUsuario()) ? null : toDto(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoContaUsuario()),
                solicitacaoCriacaoUsuarioPaciente.getNome(), solicitacaoCriacaoUsuarioPaciente.getEmail(), solicitacaoCriacaoUsuarioPaciente.getSenha(),
                Objects.isNull(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoEnderecoUsuario()) ? null :
                        SolicitacaoCriacaoUsuarioPacienteEnderecoMapper.toDto(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoEnderecoUsuario()),
                solicitacaoCriacaoUsuarioPaciente.getCelular());
    }

    public static SolicitacaoTrocaSenhaUsuarioDto toDto(SolicitacaoTrocaSenhaUsuario solicitacaoTrocaSenhaUsuario) {
        return new  SolicitacaoTrocaSenhaUsuarioDto(solicitacaoTrocaSenhaUsuario.getId(), solicitacaoTrocaSenhaUsuario.getNovaSenha(),
                Objects.isNull(solicitacaoTrocaSenhaUsuario.getSolicitacaoContaUsuario()) ? null : toDto(solicitacaoTrocaSenhaUsuario.getSolicitacaoContaUsuario()),
                Objects.isNull(solicitacaoTrocaSenhaUsuario.getUsuario()) ? null : UsuarioMapper.toDto(solicitacaoTrocaSenhaUsuario.getUsuario()));
    }

    public static SolicitacaoCriacaoUsuarioPacienteResponse toResponse(SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPaciente) {
        return new SolicitacaoCriacaoUsuarioPacienteResponse(solicitacaoCriacaoUsuarioPaciente.getId(),
                Objects.isNull(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoContaUsuario()) ? null :
                        toResponse(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoContaUsuario(), solicitacaoCriacaoUsuarioPaciente.getEmail()),
                solicitacaoCriacaoUsuarioPaciente.getNome(), solicitacaoCriacaoUsuarioPaciente.getEmail(),
                Objects.isNull(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoEnderecoUsuario()) ? null :
                        SolicitacaoCriacaoUsuarioPacienteEnderecoMapper.toResponse(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoEnderecoUsuario()),
                solicitacaoCriacaoUsuarioPaciente.getCelular());
    }

    public static SolicitacaoTrocaSenhaUsuarioResponse toResponse(SolicitacaoTrocaSenhaUsuario solicitacaoTrocaSenhaUsuario) {
        return new SolicitacaoTrocaSenhaUsuarioResponse(solicitacaoTrocaSenhaUsuario.getId(),
                Objects.isNull(solicitacaoTrocaSenhaUsuario.getSolicitacaoContaUsuario()) ? null : toResponse(solicitacaoTrocaSenhaUsuario.getSolicitacaoContaUsuario(),
                        Objects.isNull(solicitacaoTrocaSenhaUsuario.getUsuario()) ? null : solicitacaoTrocaSenhaUsuario.getUsuario().getEmail()),
                solicitacaoTrocaSenhaUsuario.getUsuario().getId());
    }

    public static SolicitacaoContaUsuario toEntidade(SolicitacaoContaUsuarioDto solicitacaoContaUsuarioDto) {
        return new SolicitacaoContaUsuario(solicitacaoContaUsuarioDto.id(), solicitacaoContaUsuarioDto.codigo(),
                solicitacaoContaUsuarioDto.validade(), solicitacaoContaUsuarioDto.consumida());
    }

    public static SolicitacaoContaUsuarioDto toDto(SolicitacaoContaUsuario solicitacaoContaUsuario) {
        return new SolicitacaoContaUsuarioDto(solicitacaoContaUsuario.getId(), solicitacaoContaUsuario.getCodigo(),
                solicitacaoContaUsuario.getValidade(), solicitacaoContaUsuario.isConsumida());
    }

    private static SolicitacaoContaUsuarioResponse toResponse(SolicitacaoContaUsuario solicitacaoContaUsuario, String email) {
        return new SolicitacaoContaUsuarioResponse(solicitacaoContaUsuario.getId(), solicitacaoContaUsuario.getValidade(),
                solicitacaoContaUsuario.isConsumida(), email);
    }
}
