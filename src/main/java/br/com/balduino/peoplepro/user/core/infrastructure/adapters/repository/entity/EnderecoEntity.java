package br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class EnderecoEntity {

    @Column(name = "rua", nullable = false, length = 256)
    String rua;

    @Column(name = "numero", nullable = false, length = 64)
    String numero;

    @Column(name = "complemento", nullable = false, length = 128)
    String complemento;

    @Column(name = "bairro", nullable = false, length = 128)
    String bairro;

    @Column(name = "cidade", nullable = false, length = 128)
    String cidade;

    @Column(name = "estado", nullable = false, length = 128)
    String estado;

    @Column(name = "cep", nullable = false, length = 16)
    String cep;
}
