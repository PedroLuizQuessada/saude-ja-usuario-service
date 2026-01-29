package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.EnderecoUsuario;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.exceptions.BadArgumentException;
import com.example.saudejausuarioservice.gateways.SolicitacaoContaUsuarioGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;
import dtos.requests.AtualizarProprioUsuarioRequest;

import java.util.Objects;

public class AtualizarUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;
    private final ConferirDisponibilidadeEmailUsuarioUseCase conferirDisponibilidadeEmailUsuarioUseCase;

    public AtualizarUsuarioUseCase(SolicitacaoContaUsuarioGateway solicitacaoContaUsuarioGateway, UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
        this.conferirDisponibilidadeEmailUsuarioUseCase = new ConferirDisponibilidadeEmailUsuarioUseCase(solicitacaoContaUsuarioGateway, usuarioGateway);
    }

    public Usuario executar(Long id, AtualizarProprioUsuarioRequest atualizarProprioUsuarioRequest) {
        Usuario usuario = usuarioGateway.getUsuarioById(id);
        EnderecoUsuario enderecoUsuario = usuario.getEnderecoUsuario();

        if (Objects.isNull(atualizarProprioUsuarioRequest.enderecoUsuario()))
            throw new BadArgumentException("O usuário deve possuir um endereço.");

        String mensagemErro = "";
        try {
            enderecoUsuario.setEstado(atualizarProprioUsuarioRequest.enderecoUsuario().estado());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            enderecoUsuario.setCidade(atualizarProprioUsuarioRequest.enderecoUsuario().cidade());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            enderecoUsuario.setBairro(atualizarProprioUsuarioRequest.enderecoUsuario().bairro());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            enderecoUsuario.setRua(atualizarProprioUsuarioRequest.enderecoUsuario().rua());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            enderecoUsuario.setNumero(atualizarProprioUsuarioRequest.enderecoUsuario().numero());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            enderecoUsuario.setComplemento(atualizarProprioUsuarioRequest.enderecoUsuario().complemento());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            enderecoUsuario.setCep(atualizarProprioUsuarioRequest.enderecoUsuario().cep());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            usuario.setNome(atualizarProprioUsuarioRequest.nome());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            usuario.setEmail(atualizarProprioUsuarioRequest.email());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            usuario.setEnderecoUsuario(enderecoUsuario);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }
        try {
            usuario.setCelular(atualizarProprioUsuarioRequest.celular());
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        if (!mensagemErro.isEmpty())
            throw new BadArgumentException(mensagemErro);

        conferirDisponibilidadeEmailUsuarioUseCase.executar(atualizarProprioUsuarioRequest.email());

        return usuarioGateway.saveUsuario(UsuarioMapper.toDto(usuario));
    }
}
