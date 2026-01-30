package com.example.saudejausuarioservice.gateways;

import com.example.saudejausuarioservice.datasources.FichaPacienteDataSource;

public class FichaPacienteGateway {

    private final FichaPacienteDataSource fichaPacienteDataSource;

    public FichaPacienteGateway(FichaPacienteDataSource fichaPacienteDataSource) {
        this.fichaPacienteDataSource = fichaPacienteDataSource;
    }

    public void apagarFichaPacienteByPacienteId(Long idPaciente) {
        fichaPacienteDataSource.apagarFichaPacienteByPacienteId(idPaciente);
    }
}
