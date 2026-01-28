package com.example.saudejausuarioservice.infrastructure.input.api.controllers.solicitacaocontausuario;

import com.example.saudejausuarioservice.controllers.SolicitacaoContaUsuarioController;
import com.example.saudejausuarioservice.datasources.SolicitacaoContaUsuarioDataSource;
import com.example.saudejausuarioservice.datasources.UsuarioDataSource;
import dtos.requests.ConsumirSolicitacaoRequest;
import dtos.requests.SolicitacaoCriacaoUsuarioPacienteRequest;
import dtos.requests.SolicitacaoTrocaSenhaUsuarioRequest;
import dtos.responses.SolicitacaoCriacaoUsuarioPacienteResponse;
import dtos.responses.SolicitacaoTrocaSenhaUsuarioResponse;
import dtos.responses.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/solicitacoes-conta")
@Tag(name = "Solicitações Conta API V1", description = "Versão 1 do controlador referente a solicitações de contas de usuários")
@Profile("api")
public class SolicitacaoContaUsuarioControllerV1 {

    private final SolicitacaoContaUsuarioController solicitacaoContaUsuarioController;

    public SolicitacaoContaUsuarioControllerV1(SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource, UsuarioDataSource usuarioDataSource) {
        this.solicitacaoContaUsuarioController = new SolicitacaoContaUsuarioController(solicitacaoContaUsuarioDataSource, usuarioDataSource);
    }

    @Operation(summary = "Solicita a criação de um usuário paciente",
            description = "Endpoint liberado para usuários não autenticados")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Solicitação criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SolicitacaoCriacaoUsuarioPacienteResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos do usuário paciente a ser criado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PostMapping("/paciente")
    public ResponseEntity<SolicitacaoCriacaoUsuarioPacienteResponse> solicitarCriacaoUsuarioPaciente(@RequestBody @Valid SolicitacaoCriacaoUsuarioPacienteRequest solicitacaoCriacaoUsuarioPacienteRequest) {
        log.info("Criando solicitação para criação de usuário paciente {}", solicitacaoCriacaoUsuarioPacienteRequest.email());
        SolicitacaoCriacaoUsuarioPacienteResponse solicitacaoCriacaoUsuarioPacienteResponse =
                solicitacaoContaUsuarioController.solicitarCriacaoUsuarioPaciente(solicitacaoCriacaoUsuarioPacienteRequest);
        log.info("Criada solicitação para criação de usuário paciente {}", solicitacaoCriacaoUsuarioPacienteRequest.email());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitacaoCriacaoUsuarioPacienteResponse);
    }

    @Operation(summary = "Solicita troca de senha de um usuário",
            description = "Endpoint liberado para usuários não autenticados")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Solicitação criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SolicitacaoTrocaSenhaUsuarioResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Valor inválido para a nova senha",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PostMapping("/senha")
    public ResponseEntity<SolicitacaoTrocaSenhaUsuarioResponse> solicitarTrocaSenhaUsuario(@RequestBody @Valid SolicitacaoTrocaSenhaUsuarioRequest solicitacaoTrocaSenhaUsuarioRequest) {
        log.info("Criando solicitação para troca de senha do usuário {}", solicitacaoTrocaSenhaUsuarioRequest.email());
        SolicitacaoTrocaSenhaUsuarioResponse solicitacaoTrocaSenhaUsuarioResponse =
                solicitacaoContaUsuarioController.solicitarTrocaSenhaUsuario(solicitacaoTrocaSenhaUsuarioRequest);
        log.info("Criada solicitação para troca de senha do usuário {}", solicitacaoTrocaSenhaUsuarioRequest.email());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitacaoTrocaSenhaUsuarioResponse);
    }

    @Operation(summary = "Consume solicitação de criação de um usuário paciente",
            description = "Endpoint liberado para usuários não autenticados")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Solicitação consumida e usuário paciente criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Código inválido ou solicitação já consumida ou vencida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Solicitação não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PutMapping("/paciente/{id}")
    public ResponseEntity<UsuarioResponse> solicitarCriacaoUsuarioPaciente(@PathVariable("id") Long solicitacaoContaId,
                                                                           @RequestBody @Valid ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        log.info("Consumindo solicitação {} para criação de usuário paciente", solicitacaoContaId);
        UsuarioResponse usuarioResponse = solicitacaoContaUsuarioController.criarUsuarioPaciente(solicitacaoContaId, consumirSolicitacaoRequest);
        log.info("Consumindo solicitação {} para criação de usuário paciente", solicitacaoContaId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioResponse);
    }

    @Operation(summary = "Consume solicitação de troca de senha de um usuário",
            description = "Endpoint liberado para usuários não autenticados")
    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    description = "Solicitação consumida e senha do usuário atualizada com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Código inválido ou solicitação já consumida ou vencida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Solicitação não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PutMapping("/senha/{id}")
    public ResponseEntity<Void> trocarSenhaUsuario(@PathVariable("id") Long solicitacaoContaId,
                                                   @RequestBody @Valid ConsumirSolicitacaoRequest consumirSolicitacaoRequest) {
        log.info("Consumindo solicitação {} para troca de senha de usuário", solicitacaoContaId);
        solicitacaoContaUsuarioController.trocarSenhaUsuario(solicitacaoContaId, consumirSolicitacaoRequest);
        log.info("Consumindo solicitação {} para troca de senha de usuário", solicitacaoContaId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
