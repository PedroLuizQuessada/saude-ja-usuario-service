package com.example.saudejausuarioservice.infrastructure.input.api.controllers.usuario;

import com.example.saudejausuarioservice.controllers.UsuarioController;
import com.example.saudejausuarioservice.datasources.SolicitacaoContaUsuarioDataSource;
import com.example.saudejausuarioservice.datasources.TokenDataSource;
import com.example.saudejausuarioservice.datasources.UsuarioDataSource;
import com.example.saudejausuarioservice.infrastructure.exceptions.TipoTokenException;
import dtos.requests.AtualizarProprioUsuarioRequest;
import dtos.requests.CriarUsuarioRequest;
import dtos.responses.CredenciaisUsuarioResponse;
import dtos.responses.UsuarioResponse;
import enums.TipoTokenEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/usuarios")
@Tag(name = "Usuários API V1", description = "Versão 1 do controlador referente a usuários")
@Profile("api")
public class UsuarioControllerV1 {

    private final UsuarioController usuarioController;

    public UsuarioControllerV1(UsuarioDataSource usuarioDataSource, TokenDataSource tokenDataSource, SolicitacaoContaUsuarioDataSource solicitacaoContaUsuarioDataSource) {
        this.usuarioController = new UsuarioController(usuarioDataSource, tokenDataSource, solicitacaoContaUsuarioDataSource);
    }

    @Operation(summary = "Cria um usuário",
            description = "Endpoint restrito a usuários ADMIN",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos do usuário a ser criado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'ADMIN'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@AuthenticationPrincipal Jwt jwt,
                                                        @RequestBody @Valid CriarUsuarioRequest criarUsuarioRequest) {
        log.info("Admin {} criando usuário {}", jwt.getSubject(), criarUsuarioRequest.email());
        UsuarioResponse usuarioResponse = usuarioController.criarUsuario(criarUsuarioRequest);
        log.info("Admin {} criou usuário {}", jwt.getSubject(), criarUsuarioRequest.email());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioResponse);
    }

    @Operation(summary = "Recupera as credenciais de um usuário",
            description = "Endpoint restrito a serviços",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Credenciais recuperadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CredenciaisUsuarioResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Token autenticado não é de serviço",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @GetMapping("/credenciais/{email}")
    public ResponseEntity<CredenciaisUsuarioResponse> getCredenciaisUsuario(@AuthenticationPrincipal Jwt jwt,
                                                                            @PathVariable("email") String usuarioEmail) {
        if (Objects.equals(jwt.getClaims().get("tipo_token"), TipoTokenEnum.SERVICO))
            throw new TipoTokenException();
        log.info("Serviço {} recuperando credenciais do usuário {}", jwt.getSubject(), usuarioEmail);
        CredenciaisUsuarioResponse credenciaisUsuarioResponse = usuarioController.getCredenciaisUsuario(usuarioEmail);
        log.info("Serviço {} recuperou credenciais do usuário {}", jwt.getSubject(), usuarioEmail);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(credenciaisUsuarioResponse);
    }

    @Operation(summary = "Atualiza seu próprio usuário",
            description = "Endpoint liberado a qualquer usuário autenticado",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos do usuário a ser atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PutMapping
    public ResponseEntity<UsuarioResponse> atualizarProprioUsuario(@AuthenticationPrincipal Jwt jwt,
                                                                   @RequestBody @Valid AtualizarProprioUsuarioRequest atualizarProprioUsuarioRequest) {
        log.info("Usuário {} atualizando seu próprio usuário", jwt.getSubject());
        UsuarioResponse usuarioResponse = usuarioController.atualizarProprioUsuario(jwt.getTokenValue(), atualizarProprioUsuarioRequest);
        log.info("Usuário {} atualizou seu próprio usuário", jwt.getSubject());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioResponse);
    }

    @Operation(summary = "Apaga o seu próprio usuário",
            description = "Endpoint liberado a qualquer usuário autenticado",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    description = "Usuário apagado com sucesso"),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping
    public ResponseEntity<Void> apagarProprioUsuario(@AuthenticationPrincipal Jwt jwt, HttpSession httpSession) {
        log.info("Usuário {} apagando próprio usuário", jwt.getSubject());
        usuarioController.apagarProprioUsuario(jwt.getTokenValue());
        httpSession.invalidate();
        log.info("Usuário {} apagou próprio usuário", jwt.getSubject());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Apaga usuário",
            description = "Endpoint restrito a usuários ADMIN",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    description = "Usuário apagado com sucesso"),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'ADMIN'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarUsuario(@AuthenticationPrincipal Jwt jwt, @PathVariable("id") Long usuarioId) {
        log.info("Admin {} apagando usuário {}", jwt.getSubject(), usuarioId);
        usuarioController.apagarUsuario(usuarioId);
        log.info("Admin {} apagou usuário {}", jwt.getSubject(), usuarioId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
