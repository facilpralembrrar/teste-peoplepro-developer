package br.com.balduino.peoplepro.user.core.application.rest.controllers;

import br.com.balduino.peoplepro.user.core.application.rest.mapper.BusinessMapper;
import br.com.balduino.peoplepro.user.core.application.rest.request.CreateUsuarioRequest;
import br.com.balduino.peoplepro.user.core.application.rest.request.UpdateUsuarioRequest;
import br.com.balduino.peoplepro.user.core.application.rest.response.CreateUsuarioResponse;
import br.com.balduino.peoplepro.user.core.application.rest.response.GetAllUsuariosResponse;
import br.com.balduino.peoplepro.user.core.application.rest.response.GetUsuarioByIdResponse;
import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Tag(name = "Usuários API", description = "APIs usadas para controle de usuários.")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private GetUsuarioByIdUseCase getUsuarioByIdUseCase;

    @Autowired
    private GetAllUsuariosUseCase getAllUsuariosUseCase;

    @Autowired
    private CreateUsuarioUseCase createUsuarioUseCase;

    @Autowired
    private UpdateUsuarioUseCase updateUsuarioUseCase;

    @Autowired
    private DeleteUsuarioUseCase deleteUsuarioUseCase;

    @Autowired
    private BusinessMapper businessMapper;

    @Operation(
            summary = "Recupera um usuário através do seu identificador (UUID)",
            description = "Recupera um usuário através do identificador único do tipo UUID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = GetUsuarioByIdResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
    })
    @GetMapping("/{usuarioId}")
    public ResponseEntity<GetUsuarioByIdResponse> getUsuario(
            @Parameter(
                    name = "usuarioId",
                    description = "Usuário ID",
                    example = "a62c871a-ada9-4771-9ba5-e93e55d685f7",
                    required = true)
            @PathVariable(value = "usuarioId", required = true) UUID usuarioId) {
        GetUsuarioByIdResponse response = businessMapper.toGetUsuarioByIdResponse(
                getUsuarioByIdUseCase.getUsuarioById(usuarioId));

        return ResponseEntity.ok().body(response);
    }

    @Operation(
            summary = "Recupera todos os usuários.",
            description = "Recupera todos os usuários.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = GetAllUsuariosResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
    })
    @GetMapping("/")
    public ResponseEntity<GetAllUsuariosResponse> getAll(
            @Parameter(
                    name = "page",
                    description = "Página de usuários.",
                    example = "0",
                    required = false)
            @RequestParam(defaultValue = "0") Integer page,
            @Parameter(
                    name = "pageSize",
                    description = "Tamanho da página.",
                    example = "10",
                    required = false)
            @RequestParam(defaultValue = "5") Integer pageSize) {
        GetAllUsuariosResponse response = businessMapper.toGetAllUsuariosResponse(
                getAllUsuariosUseCase.getAllUsuarios(page, pageSize));

        return ResponseEntity.ok().body(response);
    }

    @Operation(
            summary = "Cria um novo usuário.",
            description = "Cria um novo usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/")
    public ResponseEntity<CreateUsuarioResponse> create(
            @RequestBody CreateUsuarioRequest createUsuarioRequest) {
        Usuario usuario = createUsuarioUseCase.create(
                createUsuarioRequest.getCpf(),
                createUsuarioRequest.getNome(),
                createUsuarioRequest.getDataNascimento(),
                businessMapper.toEndereco(createUsuarioRequest.getEndereco()));

        return ResponseEntity.status(HttpStatus.CREATED).body(businessMapper.toCreateUsuarioResponse(usuario));
    }

    @Operation(
            summary = "Atualiza um usuário.",
            description = "Atualiza um usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @PatchMapping("/{usuarioId}")
    public ResponseEntity<Void> update(
            @Parameter(
                    name = "usuarioId",
                    description = "Usuário ID",
                    example = "a62c871a-ada9-4771-9ba5-e93e55d685f7",
                    required = true)
            @PathVariable(value = "usuarioId", required = true) UUID usuarioId,
            @RequestBody UpdateUsuarioRequest updateUsuarioRequest) {
        updateUsuarioUseCase.update(
                usuarioId,
                updateUsuarioRequest.getCpf(),
                updateUsuarioRequest.getNome(),
                updateUsuarioRequest.getDataNascimento(),
                businessMapper.toEndereco(updateUsuarioRequest.getEndereco()),
                updateUsuarioRequest.getStatus());

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Inativa um usuário.",
            description = "Inativa um usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> delete(
            @Parameter(
                    name = "usuarioId",
                    description = "Usuário ID",
                    example = "a62c871a-ada9-4771-9ba5-e93e55d685f7",
                    required = true)
            @PathVariable(value = "usuarioId", required = true) UUID usuarioId) {
        deleteUsuarioUseCase.update(usuarioId);

        return ResponseEntity.noContent().build();
    }

    private ZonedDateTime getZonedDateTimeUTC(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = null;

        if (localDateTime != null) {
            zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
        }

        return zonedDateTime;
    }
}
