package com.example.saudejausuarioservice.usecases;

import com.example.saudejausuarioservice.entidades.Usuario;
import com.example.saudejausuarioservice.gateways.FichaPacienteGateway;
import com.example.saudejausuarioservice.gateways.UsuarioGateway;
import enums.TipoUsuarioEnum;

import java.util.Objects;

public class ApagarUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;
    private final FichaPacienteGateway fichaPacienteGateway;

    public ApagarUsuarioUseCase(UsuarioGateway usuarioGateway, FichaPacienteGateway fichaPacienteGateway) {
        this.usuarioGateway = usuarioGateway;
        this.fichaPacienteGateway = fichaPacienteGateway;
    }

    public void executar(Long id) {
        Usuario usuario = usuarioGateway.getUsuarioById(id);
        usuarioGateway.deleteUsuarioById(usuario.getId());
        if (Objects.equals(usuario.getTipo(), TipoUsuarioEnum.PACIENTE))
            fichaPacienteGateway.apagarFichaPacienteByPacienteId(usuario.getId());
    }
}
