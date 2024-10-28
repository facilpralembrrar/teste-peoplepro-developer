package br.com.balduino.peoplepro.user.core.application.rest.request;

import br.com.balduino.peoplepro.user.core.domain.model.UsuarioStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUsuarioRequest {

    @Schema(name = "cpf", example = "508.527.380-05", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;

    @Schema(name = "nome", example = "Tuthur", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @Schema(name = "dataNascimento", type = "string", pattern = "[0-9]{2}-[0-9]{2}-[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{4}", example = "12-06-2024", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    private CreateUsuarioEnderecoRequest endereco;

    @Schema(name = "status", example = "ATIVO", requiredMode = Schema.RequiredMode.REQUIRED)
    private UsuarioStatus status;
}
