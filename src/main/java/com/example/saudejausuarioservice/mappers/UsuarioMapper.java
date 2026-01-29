package com.example.saudejausuarioservice.mappers;

import com.example.saudejausuarioservice.dtos.UsuarioDto;
import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPaciente;
import com.example.saudejausuarioservice.entidades.Usuario;
import dtos.requests.CriarUsuarioRequest;
import dtos.requests.SolicitacaoCriacaoUsuarioPacienteRequest;
import dtos.responses.UsuarioResponse;
import enums.TipoUsuarioEnum;

import java.util.Objects;

public class UsuarioMapper {
    private UsuarioMapper() {}

    public static Usuario toEntidade(SolicitacaoCriacaoUsuarioPacienteRequest solicitacaoCriacaoUsuarioPacienteRequest) {
        return new Usuario(null, solicitacaoCriacaoUsuarioPacienteRequest.nome(), solicitacaoCriacaoUsuarioPacienteRequest.email(),
                solicitacaoCriacaoUsuarioPacienteRequest.senha(), TipoUsuarioEnum.PACIENTE,
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteRequest.enderecoUsuario()) ? null :
                        EnderecoUsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteRequest.enderecoUsuario()),
                solicitacaoCriacaoUsuarioPacienteRequest.celular(), true);
    }

    public static Usuario toEntidade(CriarUsuarioRequest solicitacaoCriacaoUsuarioPacienteRequest) {
        return new Usuario(null, solicitacaoCriacaoUsuarioPacienteRequest.nome(), solicitacaoCriacaoUsuarioPacienteRequest.email(),
                solicitacaoCriacaoUsuarioPacienteRequest.senha(), solicitacaoCriacaoUsuarioPacienteRequest.tipo(),
                Objects.isNull(solicitacaoCriacaoUsuarioPacienteRequest.enderecoUsuario()) ? null :
                        EnderecoUsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteRequest.enderecoUsuario()),
                solicitacaoCriacaoUsuarioPacienteRequest.celular(), true);
    }

    public static Usuario toEntidade(UsuarioDto usuarioDto) {
        return new Usuario(usuarioDto.id(), usuarioDto.nome(), usuarioDto.email(), usuarioDto.senha(), usuarioDto.tipo(),
                Objects.isNull(usuarioDto.enderecoUsuario()) ? null : EnderecoUsuarioMapper.toEntidade(usuarioDto.enderecoUsuario()),
                usuarioDto.celular(), false);
    }

    public static Usuario toEntidade(SolicitacaoCriacaoUsuarioPaciente solicitacaoCriacaoUsuarioPaciente) {
        return new Usuario(solicitacaoCriacaoUsuarioPaciente.getId(), solicitacaoCriacaoUsuarioPaciente.getNome(),
                solicitacaoCriacaoUsuarioPaciente.getEmail(), solicitacaoCriacaoUsuarioPaciente.getSenha(), TipoUsuarioEnum.PACIENTE,
                Objects.isNull(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoEnderecoUsuario()) ? null :
                        EnderecoUsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPaciente.getSolicitacaoEnderecoUsuario()),
                solicitacaoCriacaoUsuarioPaciente.getCelular(), false);
    }

    public static UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTipo(),
                Objects.isNull(usuario.getEnderecoUsuario()) ? null : EnderecoUsuarioMapper.toDto(usuario.getEnderecoUsuario()),
                usuario.getCelular());
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTipo(),
                Objects.isNull(usuario.getEnderecoUsuario()) ? null : EnderecoUsuarioMapper.toResponse(usuario.getEnderecoUsuario()),
                usuario.getCelular());
    }
}
