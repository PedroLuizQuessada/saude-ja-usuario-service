package com.example.saudejausuarioservice.infrastructure.client.restclient.services;

import com.example.saudejausuarioservice.controllers.AutenticacaoController;
import com.example.saudejausuarioservice.datasources.AutenticacaoDataSource;
import com.example.saudejausuarioservice.datasources.FichaPacienteDataSource;
import com.example.saudejausuarioservice.exceptions.NaoEncontradoException;
import com.example.saudejausuarioservice.infrastructure.exceptions.ForbiddenException;
import com.example.saudejausuarioservice.infrastructure.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Profile("restclient")
public class FichaPacienteRestClientImpl implements FichaPacienteDataSource {

    @Value("${ficha-paciente-service.base-url}")
    private String urlBase;

    @Value("${ficha-paciente-service.audiencia}")
    private String audiencia;

    private final RestClient client;

    private final AutenticacaoController autenticacaoController;

    private final ClientResponseService clientResponseService;

    public FichaPacienteRestClientImpl(RestClient client, AutenticacaoDataSource autenticacaoDataSource, ClientResponseService clientResponseService) {
        this.client = client;
        this.autenticacaoController = new AutenticacaoController(autenticacaoDataSource);
        this.clientResponseService = clientResponseService;
    }

    @Override
    public void apagarFichaPacienteByPacienteId(Long idPaciente) {
        client.delete()
            .uri(urlBase + "/api/v1/fichas-paciente/" + idPaciente)
            .header("Authorization", "Bearer " + autenticacaoController.gerarTokenServico(audiencia))
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->
                    {
                        if (res.getStatusCode().equals(HttpStatus.UNAUTHORIZED))
                            throw new UnauthorizedException();
                        if (res.getStatusCode().equals(HttpStatus.FORBIDDEN))
                            throw new ForbiddenException();
                        if (res.getStatusCode().equals(HttpStatus.NOT_FOUND))
                            throw new NaoEncontradoException(String.format("Ficha de paciente %d não encontrado", idPaciente));
                        else {
                            String body = clientResponseService.getResponseBody(res);
                            throw new RuntimeException("Falha no serviço de fichas de paciente (ficha-paciente-service). Corpo: " + body);
                        }
                    }
            )
            .onStatus(HttpStatusCode::is5xxServerError, (req, res) ->
                    {
                        String body = clientResponseService.getResponseBody(res);
                        throw new RuntimeException("Falha no serviço de fichas de paciente (ficha-paciente-service). Corpo: " + body);
                    }
            )
            .toEntity(Void.class);
    }
}
