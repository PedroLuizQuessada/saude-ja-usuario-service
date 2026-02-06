package com.example.saudejausuarioservice.datasources;

import com.example.saudejausuarioservice.dtos.UsuarioDto;
import com.example.saudejausuarioservice.dtos.UsuarioEmailDtoPage;

import java.util.List;
import java.util.Optional;

public interface UsuarioDataSource {
    Long countByEmail(String email);
    Optional<UsuarioDto> getUsuarioByEmail(String email);
    Optional<UsuarioDto> getUsuarioById(Long id);
    UsuarioDto saveUsuario(UsuarioDto usuarioDto);
    void deleteUsuarioById(Long id);
    UsuarioEmailDtoPage getUsuarioPacienteEmailFromId(int page, int size, List<Long> ids);
}
