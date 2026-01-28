package com.example.saudejausuarioservice.infrastructure.persistence.jpa.repos;

import com.example.saudejausuarioservice.datasources.UsuarioDataSource;
import com.example.saudejausuarioservice.dtos.UsuarioDto;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.mappers.UsuarioJpaDtoMapper;
import com.example.saudejausuarioservice.infrastructure.persistence.jpa.models.UsuarioJpa;
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
public class UsuarioRepoJpaImpl implements UsuarioDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioJpaDtoMapper usuarioJpaDtoMapper;

    @Override
    public Long countByEmail(String email) {
        Query query = entityManager.createQuery("SELECT count(*) FROM UsuarioJpa usuario WHERE usuario.email = :email");
        query.setParameter("email", email);
        return (Long) query.getSingleResult();
    }

    @Override
    public Optional<UsuarioDto> getUsuarioByEmail(String email) {
        Query query = entityManager.createQuery("SELECT usuario FROM UsuarioJpa usuario WHERE usuario.email = :email");
        query.setParameter("email", email);
        try {
            UsuarioJpa usuarioJpa = (UsuarioJpa) query.getSingleResult();
            return Optional.of(usuarioJpaDtoMapper.toDto(usuarioJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UsuarioDto> getUsuarioById(Long id) {
        Query query = entityManager.createQuery("SELECT usuario FROM UsuarioJpa usuario WHERE usuario.id = :id");
        query.setParameter("id", id);
        try {
            UsuarioJpa usuarioJpa = (UsuarioJpa) query.getSingleResult();
            return Optional.of(usuarioJpaDtoMapper.toDto(usuarioJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {
        UsuarioJpa usuarioJpa = usuarioJpaDtoMapper.toJpa(usuarioDto);
        usuarioJpa = entityManager.merge(usuarioJpa);
        return usuarioJpaDtoMapper.toDto(usuarioJpa);
    }

    @Override
    @Transactional
    public void deleteUsuarioById(Long id) {
        UsuarioJpa usuario = entityManager.find(UsuarioJpa.class, id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }
}
