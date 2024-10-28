package br.com.balduino.peoplepro.user.core.application.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUsuarioByIdEnderecoResponse {

    @Schema(name = "rua", example = "Rua Visconde de Mayrink")
    private String rua;

    @Schema(name = "numero", example = "130-A")
    private String numero;

    @Schema(name = "complemento", example = "Torre A")
    private String complemento;

    @Schema(name = "bairro", example = "Cidade Tiradentes")
    private String bairro;

    @Schema(name = "cidade", example = "São Paulo")
    private String cidade;

    @Schema(name = "estado", example = "SP")
    private String estado;

    @Schema(name = "cep", example = "08471-760")
    private String cep;
}
