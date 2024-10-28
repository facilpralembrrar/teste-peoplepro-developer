package br.com.balduino.peoplepro.user.core.application.rest.response;

import br.com.balduino.peoplepro.user.core.domain.model.UsuarioStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUsuarioByIdResponse {
    private UUID id;

    @Schema(name = "cpf", example = "508.527.380-05")
    private String cpf;

    @Schema(name = "nome", example = "Tuthur")
    private String nome;

    @Schema(name = "dataNascimento", type = "string", pattern = "[0-9]{2}-[0-9]{2}-[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{4}", example = "12-06-2024 10:15:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataNascimento;

    private GetUsuarioByIdEnderecoResponse endereco;

    @Schema(name = "status", example = "ATIVO")
    private UsuarioStatus status;

    @Schema(name = "createdAt", type = "string", pattern = "[0-9]{2}-[0-9]{2}-[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{4}", example = "12-06-2024 10:15:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(name = "createdBy", example = "Haltavo")
    private String createdBy;

    @Schema(name = "updatedAt", type = "string", pattern = "[0-9]{2}-[0-9]{2}-[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{4}", example = "12-06-2024 10:15:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @Schema(name = "updatedBy", example = "Zokuore")
    private String updatedBy;
}
