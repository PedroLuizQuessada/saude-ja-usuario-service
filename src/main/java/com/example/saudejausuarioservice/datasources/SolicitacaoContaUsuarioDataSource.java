package com.example.saudejausuarioservice.datasources;

import com.example.saudejausuarioservice.dtos.SolicitacaoContaUsuarioDto;
import com.example.saudejausuarioservice.dtos.SolicitacaoCriacaoUsuarioPacienteDto;
import com.example.saudejausuarioservice.dtos.SolicitacaoTrocaSenhaUsuarioDto;

import java.util.Optional;

public interface SolicitacaoContaUsuarioDataSource {
    SolicitacaoCriacaoUsuarioPacienteDto criarSolicitacaoCriacaoUsuarioPaciente(SolicitacaoCriacaoUsuarioPacienteDto solicitacaoCriacaoUsuarioPacienteDto);
    SolicitacaoTrocaSenhaUsuarioDto criarSolicitacaoTrocaSenhaUsuario(SolicitacaoTrocaSenhaUsuarioDto solicitacaoTrocaSenhaUsuarioDto);
    Long countByEmailWhereIsValidaAndNaoConsumida(String email);
    Optional<SolicitacaoContaUsuarioDto> getSolicitacaoContaUsuarioById(Long id);
    Optional<SolicitacaoTrocaSenhaUsuarioDto> getSolicitacaoTrocaSenhaUsuarioById(Long id);
    Optional<SolicitacaoCriacaoUsuarioPacienteDto> getSolicitacaoCriacaoUsuarioPacienteById(Long id);
    void consumirSolicitacaoContaUsuario(SolicitacaoContaUsuarioDto solicitacaoContaUsuarioDto);
}
