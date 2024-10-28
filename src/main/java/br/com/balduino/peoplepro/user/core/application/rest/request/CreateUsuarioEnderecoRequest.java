package br.com.balduino.peoplepro.user.core.application.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUsuarioEnderecoRequest {

    @Schema(name = "rua", example = "Rua Visconde de Mayrink", requiredMode = Schema.RequiredMode.REQUIRED)
    private String rua;

    @Schema(name = "numero", example = "130-A", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numero;

    @Schema(name = "complemento", example = "Torre A", requiredMode = Schema.RequiredMode.REQUIRED)
    private String complemento;

    @Schema(name = "bairro", example = "Cidade Tiradentes", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bairro;

    @Schema(name = "cidade", example = "São Paulo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cidade;

    @Schema(name = "estado", example = "SP", requiredMode = Schema.RequiredMode.REQUIRED)
    private String estado;

    @Schema(name = "cep", example = "08471-760", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cep;
}
