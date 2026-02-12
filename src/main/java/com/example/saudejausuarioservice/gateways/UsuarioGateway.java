package com.example.saudejausuarioservice.gateways;

import com.example.saudejausuarioservice.datasources.UsuarioDataSource;
import com.example.saudejausuarioservice.dtos.UsuarioDto;
import com.example.saudejausuarioservice.dtos.UsuarioEmailDtoPage;
import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.entidades.UsuarioEmailPage;
import com.example.saudejausuarioservice.exceptions.NaoEncontradoException;
import com.example.saudejausuarioservice.mappers.UsuarioMapper;

import java.util.List;
import java.util.Optional;

public class UsuarioGateway {
    private final UsuarioDataSource usuarioDataSource;

    public UsuarioGateway(UsuarioDataSource usuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
    }

    public Long countByEmail(String email) {
        return usuarioDataSource.countByEmail(email);
    }

    public Usuario getUsuarioByEmail(String email) {
        Optional<UsuarioDto> optionalUsuarioDto = usuarioDataSource.getUsuarioByEmail(email);
        if (optionalUsuarioDto.isEmpty())
            throw new NaoEncontradoException(String.format("Usuário %s não encontrado.", email));
        return UsuarioMapper.toEntidade(optionalUsuarioDto.get());
    }

    public Usuario getUsuarioById(Long id) {
        Optional<UsuarioDto> optionalUsuarioDto = usuarioDataSource.getUsuarioById(id);
        if (optionalUsuarioDto.isEmpty())
            throw new NaoEncontradoException(String.format("Usuário %d não encontrado.", id));
        return UsuarioMapper.toEntidade(optionalUsuarioDto.get());
    }

    public Usuario saveUsuario(UsuarioDto usuarioDto) {
        UsuarioDto usuarioDtoSalvo = usuarioDataSource.saveUsuario(usuarioDto);
        return UsuarioMapper.toEntidade(usuarioDtoSalvo);
    }

    public void deleteUsuarioById(Long id) {
        usuarioDataSource.deleteUsuarioById(id);
    }

    public UsuarioEmailPage getUsuarioPacienteEmailFromId(int page, int size, List<Long> ids) {
        UsuarioEmailDtoPage usuarioEmailDtoPage = usuarioDataSource.getUsuarioPacienteEmailFromId(page, size, ids);
        return new UsuarioEmailPage(usuarioEmailDtoPage.getPage(), usuarioEmailDtoPage.getSize(), usuarioEmailDtoPage.getContent());
    }

    public UsuarioEmailPage getUsuarioProfissionalSaudeEmailFromId(int page, int size, List<Long> ids) {
        UsuarioEmailDtoPage usuarioEmailDtoPage = usuarioDataSource.getUsuarioProfissionalSaudeEmailFromId(page, size, ids);
        return new UsuarioEmailPage(usuarioEmailDtoPage.getPage(), usuarioEmailDtoPage.getSize(), usuarioEmailDtoPage.getContent());
    }
}
