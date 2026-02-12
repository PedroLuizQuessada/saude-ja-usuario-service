package com.example.saudejausuarioservice.infrastructure.client.restclient.services;

import com.example.saudejausuarioservice.controllers.AutenticacaoController;
import com.example.saudejausuarioservice.datasources.AutenticacaoDataSource;
import com.example.saudejausuarioservice.datasources.PostoSaudeDataSource;
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
public class PostoSaudeRestClientImpl implements PostoSaudeDataSource {

    @Value("${posto-saude-service.base-url}")
    private String urlBase;

    @Value("${posto-saude-service.audiencia}")
    private String audiencia;

    private final RestClient client;

    private final AutenticacaoController autenticacaoController;

    private final ClientResponseService clientResponseService;

    public PostoSaudeRestClientImpl(RestClient client, AutenticacaoDataSource autenticacaoDataSource, ClientResponseService clientResponseService) {
        this.client = client;
        this.autenticacaoController = new AutenticacaoController(autenticacaoDataSource);
        this.clientResponseService = clientResponseService;
    }

    @Override
    public void removerPaciente(Long pacienteId) {
        client.put()
                .uri(urlBase + "/api/v1/postos-saude/pacientes/remover/" + pacienteId)
                .header("Authorization", "Bearer " + autenticacaoController.gerarTokenServico(audiencia))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->
                        {
                            if (res.getStatusCode().equals(HttpStatus.UNAUTHORIZED))
                                throw new UnauthorizedException();
                            if (res.getStatusCode().equals(HttpStatus.FORBIDDEN))
                                throw new ForbiddenException();
                            else {
                                String body = clientResponseService.getResponseBody(res);
                                throw new RuntimeException("Falha no serviço de posto de saúde (posto-saude-service). Corpo: " + body);
                            }
                        }
                )
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) ->
                        {
                            String body = clientResponseService.getResponseBody(res);
                            throw new RuntimeException("Falha no serviço de posto de saúde (posto-saude-service). Corpo: " + body);
                        }
                )
                .toEntity(Void.class);
    }

    @Override
    public void removerProfissionalSaude(Long profissionalSaudeId) {
        client.put()
                .uri(urlBase + "/api/v1/postos-saude/profissionais-saude/remover/" + profissionalSaudeId)
                .header("Authorization", "Bearer " + autenticacaoController.gerarTokenServico(audiencia))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->
                        {
                            if (res.getStatusCode().equals(HttpStatus.UNAUTHORIZED))
                                throw new UnauthorizedException();
                            if (res.getStatusCode().equals(HttpStatus.FORBIDDEN))
                                throw new ForbiddenException();
                            else {
                                String body = clientResponseService.getResponseBody(res);
                                throw new RuntimeException("Falha no serviço de posto de saúde (posto-saude-service). Corpo: " + body);
                            }
                        }
                )
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) ->
                        {
                            String body = clientResponseService.getResponseBody(res);
                            throw new RuntimeException("Falha no serviço de posto de saúde (posto-saude-service). Corpo: " + body);
                        }
                )
                .toEntity(Void.class);
    }
}
