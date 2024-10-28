package br.com.balduino.peoplepro.user.core.application.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllUsuariosResponse {
    @Schema(name = "totalPages", type = "number", example = "10")
    Integer totalPages;

    @Schema(name = "totalElements", type = "number", example = "100")
    Long totalElements;

    @Schema(name = "size", type = "number", example = "10")
    Integer size;

    @Schema(name = "number", type = "number", example = "0")
    Integer number;

    @Schema(name = "numberOfElements", type = "number", example = "10")
    Integer numberOfElements;

    @Schema(name = "content", type = "array")
    List<GetAllUsuariosUsuarioResponse> content;
}
