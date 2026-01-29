package com.example.saudejausuarioservice.infrastructure.persistence.jpa.repos;

import com.example.saudejausuarioservice.datasources.SolicitacaoContaUsuarioDataSource;
import com.example.saudejausuarioservice.dtos.SolicitacaoContaUsuarioDto;
import com.example.saudejausuarioservice.dtos.SolicitacaoCriacaoUsuarioPacienteDto;
import com.example.saudejausuarioservice.dtos.SolicitacaoTrocaSenhaUsuarioDto;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers.SolicitacaoContaUsuarioJpaDtoMapper;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers.SolicitacaoCriacaoUsuarioPacienteJpaDtoMapper;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers.SolicitacaoTrocaSenhaUsuarioJpaDtoMapper;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.SolicitacaoContaUsuarioJpa;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.SolicitacaoCriacaoUsuarioPacienteJpa;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.SolicitacaoTrocaSenhaUsuarioJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Profile("jpa")
public class SolicitacaoContaUsuarioRepoJpaImpl implements SolicitacaoContaUsuarioDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SolicitacaoContaUsuarioJpaDtoMapper solicitacaoContaUsuarioJpaDtoMapper;

    @Autowired
    private SolicitacaoTrocaSenhaUsuarioJpaDtoMapper solicitacaoTrocaSenhaUsuarioJpaDtoMapper;

    @Autowired
    private SolicitacaoCriacaoUsuarioPacienteJpaDtoMapper solicitacaoCriacaoUsuarioPacienteJpaDtoMapper;

    @Override
    @Transactional
    public SolicitacaoCriacaoUsuarioPacienteDto criarSolicitacaoCriacaoUsuarioPaciente(SolicitacaoCriacaoUsuarioPacienteDto solicitacaoCriacaoUsuarioPacienteDto) {
        SolicitacaoCriacaoUsuarioPacienteJpa solicitacaoCriacaoUsuarioPacienteJpa = solicitacaoCriacaoUsuarioPacienteJpaDtoMapper.toJpa(solicitacaoCriacaoUsuarioPacienteDto);
        solicitacaoCriacaoUsuarioPacienteJpa = entityManager.merge(solicitacaoCriacaoUsuarioPacienteJpa);
        return solicitacaoCriacaoUsuarioPacienteJpaDtoMapper.toDto(solicitacaoCriacaoUsuarioPacienteJpa);
    }

    @Override
    @Transactional
    public SolicitacaoTrocaSenhaUsuarioDto criarSolicitacaoTrocaSenhaUsuario(SolicitacaoTrocaSenhaUsuarioDto solicitacaoTrocaSenhaUsuarioDto) {
        SolicitacaoTrocaSenhaUsuarioJpa solicitacaoTrocaSenhaUsuarioJpa = solicitacaoTrocaSenhaUsuarioJpaDtoMapper.toJpa(solicitacaoTrocaSenhaUsuarioDto);
        solicitacaoTrocaSenhaUsuarioJpa = entityManager.merge(solicitacaoTrocaSenhaUsuarioJpa);
        return solicitacaoTrocaSenhaUsuarioJpaDtoMapper.toDto(solicitacaoTrocaSenhaUsuarioJpa);
    }

    @Override
    public Long countByEmailWhereIsValidaAndNaoConsumida(String email) {
        Query query = entityManager.createQuery("SELECT count(*) " +
                "FROM SolicitacaoCriacaoUsuarioPacienteJpa solicitacaoCriacaoUsuarioPaciente " +
                "WHERE solicitacaoCriacaoUsuarioPaciente.email = :email " +
                    " AND solicitacaoCriacaoUsuarioPaciente.solicitacaoContaUsuario.validade > CURRENT_TIMESTAMP " +
                    " AND solicitacaoCriacaoUsuarioPaciente.solicitacaoContaUsuario.consumida = false");
        query.setParameter("email", email);
        return (Long) query.getSingleResult();
    }

    @Override
    public Optional<SolicitacaoContaUsuarioDto> getSolicitacaoContaUsuarioById(Long id) {
        Query query = entityManager.createQuery("SELECT solicitacaoContaUsuario FROM SolicitacaoContaUsuarioJpa solicitacaoContaUsuario WHERE solicitacaoContaUsuario.id = :id");
        query.setParameter("id", id);
        try {
            SolicitacaoContaUsuarioJpa solicitacaoContaUsuarioJpa = (SolicitacaoContaUsuarioJpa) query.getSingleResult();
            return Optional.of(solicitacaoContaUsuarioJpaDtoMapper.toDto(solicitacaoContaUsuarioJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SolicitacaoTrocaSenhaUsuarioDto> getSolicitacaoTrocaSenhaUsuarioById(Long id) {
        Query query = entityManager.createQuery("SELECT solicitacaoTrocaSenhaUsuario FROM SolicitacaoTrocaSenhaUsuarioJpa solicitacaoTrocaSenhaUsuario WHERE solicitacaoTrocaSenhaUsuario.id = :id");
        query.setParameter("id", id);
        try {
            SolicitacaoTrocaSenhaUsuarioJpa solicitacaoTrocaSenhaUsuarioJpa = (SolicitacaoTrocaSenhaUsuarioJpa) query.getSingleResult();
            return Optional.of(solicitacaoTrocaSenhaUsuarioJpaDtoMapper.toDto(solicitacaoTrocaSenhaUsuarioJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SolicitacaoCriacaoUsuarioPacienteDto> getSolicitacaoCriacaoUsuarioPacienteById(Long id) {
        Query query = entityManager.createQuery("SELECT solicitacaoCriacaoUsuarioPaciente FROM SolicitacaoCriacaoUsuarioPacienteJpa solicitacaoCriacaoUsuarioPaciente WHERE solicitacaoCriacaoUsuarioPaciente.id = :id");
        query.setParameter("id", id);
        try {
            SolicitacaoCriacaoUsuarioPacienteJpa solicitacaoCriacaoUsuarioPaciente = (SolicitacaoCriacaoUsuarioPacienteJpa) query.getSingleResult();
            return Optional.of(solicitacaoCriacaoUsuarioPacienteJpaDtoMapper.toDto(solicitacaoCriacaoUsuarioPaciente));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void consumirSolicitacaoContaUsuario(SolicitacaoContaUsuarioDto solicitacaoContaUsuarioDto) {
        SolicitacaoContaUsuarioJpa solicitacaoContaUsuario = solicitacaoContaUsuarioJpaDtoMapper.toJpa(solicitacaoContaUsuarioDto);
        entityManager.merge(solicitacaoContaUsuario);
    }

    @Override
    @Transactional
    public void deleteSolicitacoesContaUsuarioVencidas() {
        Query query = entityManager.createQuery("DELETE FROM SolicitacaoCriacaoUsuarioPacienteJpa solicitacaoCriacaoUsuarioPaciente WHERE solicitacaoCriacaoUsuarioPaciente.solicitacaoContaUsuario.validade < CURRENT_TIMESTAMP");
        query.executeUpdate();
        query = entityManager.createQuery("DELETE FROM SolicitacaoTrocaSenhaUsuarioJpa solicitacaoTrocaSenhaUsuario WHERE solicitacaoTrocaSenhaUsuario.solicitacaoContaUsuario.validade < CURRENT_TIMESTAMP");
        query.executeUpdate();
    }
}
