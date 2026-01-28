package com.example.saudejausuarioservice.gateways;

import com.example.saudejausuarioservice.datasources.SolicitacaoContaUsuarioDataSource;
import com.example.saudejausuarioservice.dtos.SolicitacaoContaUsuarioDto;
import com.example.saudejausuarioservice.dtos.SolicitacaoCriacaoUsuarioPacienteDto;
import com.example.saudejausuarioservice.dtos.SolicitacaoTrocaSenhaUsuarioDto;
import com.example.saudejausuarioservice.entidades.SolicitacaoContaUsuario;
import com.example.saudejausuarioservice.entidades.SolicitacaoCriacaoUsuarioPaciente;
import com.example.saudejausuarioservice.entidades.SolicitacaoTrocaSenhaUsuario;
import com.example.saudejausuarioservice.exceptions.NaoEncontradoException;
import com.example.saudejausuarioservice.mappers.SolicitacaoContaUsuarioMapper;

import java.util.Optional;

public class SolicitacaoContaUsuarioGateway {
    private final SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource;

    public SolicitacaoContaUsuarioGateway(SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource) {
        this.solicitacaoContaUsuarioDataSource = solicitacaoContaUsuarioDataSource;
    }

    public SolicitacaoCriacaoUsuarioPaciente criarSolicitacaoCriacaoUsuarioPaciente(SolicitacaoCriacaoUsuarioPacienteDto solicitacaoCriacaoUsuarioPacienteDto) {
        SolicitacaoCriacaoUsuarioPacienteDto solicitacaoCriacaoUsuarioPacienteDtoCriada =
                solicitacaoContaUsuarioDataSource.criarSolicitacaoCriacaoUsuarioPaciente(solicitacaoCriacaoUsuarioPacienteDto);
        return SolicitacaoContaUsuarioMapper.toEntidade(solicitacaoCriacaoUsuarioPacienteDtoCriada);
    }

    public SolicitacaoTrocaSenhaUsuario criarSolicitacaoTrocaSenhaUsuario(SolicitacaoTrocaSenhaUsuarioDto solicitacaoTrocaSenhaUsuarioDto) {
        SolicitacaoTrocaSenhaUsuarioDto solicitacaoTrocaSenhaUsuarioDtoCriada =
                solicitacaoContaUsuarioDataSource.criarSolicitacaoTrocaSenhaUsuario(solicitacaoTrocaSenhaUsuarioDto);
        return SolicitacaoContaUsuarioMapper.toEntidade(solicitacaoTrocaSenhaUsuarioDtoCriada);
    }

    public Long countByEmailWhereIsValidaAndNaoConsumida(String email) {
        return solicitacaoContaUsuarioDataSource.countByEmailWhereIsValidaAndNaoConsumida(email);
    }

    public SolicitacaoTrocaSenhaUsuario getSolicitacaoTrocaSenhaUsuarioById(Long id) {
        Optional<SolicitacaoTrocaSenhaUsuarioDto> optionalSolicitacaoTrocaSenhaUsuarioDto =
                solicitacaoContaUsuarioDataSource.getSolicitacaoTrocaSenhaUsuarioById(id);
        if (optionalSolicitacaoTrocaSenhaUsuarioDto.isEmpty())
            throw new NaoEncontradoException(String.format("Solicitação de troca de senha de usuário %d não encontrada.", id));
        return SolicitacaoContaUsuarioMapper.toEntidade(optionalSolicitacaoTrocaSenhaUsuarioDto.get());
    }

    public SolicitacaoCriacaoUsuarioPaciente getSolicitacaoCriacaoUsuarioPacienteById(Long id) {
        Optional<SolicitacaoCriacaoUsuarioPacienteDto> optionalSolicitacaoCriacaoUsuarioPacienteDto =
                solicitacaoContaUsuarioDataSource.getSolicitacaoCriacaoUsuarioPacienteById(id);
        if (optionalSolicitacaoCriacaoUsuarioPacienteDto.isEmpty())
            throw new NaoEncontradoException(String.format("Solicitação de criação de usuário paciente %d não encontrada.", id));
        return SolicitacaoContaUsuarioMapper.toEntidade(optionalSolicitacaoCriacaoUsuarioPacienteDto.get());
    }

    public SolicitacaoContaUsuario getSolicitacaoContaUsuarioById(Long id) {
        Optional<SolicitacaoContaUsuarioDto> optionalSolicitacaoContaUsuarioDto =
                solicitacaoContaUsuarioDataSource.getSolicitacaoContaUsuarioById(id);
        if (optionalSolicitacaoContaUsuarioDto.isEmpty())
            throw new NaoEncontradoException(String.format("Solicitação de conta de usuário %d não encontrada.", id));
        return SolicitacaoContaUsuarioMapper.toEntidade(optionalSolicitacaoContaUsuarioDto.get());
    }

    public void consumirSolicitacaoContaUsuario(SolicitacaoContaUsuarioDto solicitacaoContaUsuarioDto) {
        solicitacaoContaUsuarioDataSource.consumirSolicitacaoContaUsuario(solicitacaoContaUsuarioDto);
    }
}
