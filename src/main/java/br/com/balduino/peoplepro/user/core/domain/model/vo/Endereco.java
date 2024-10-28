package br.com.balduino.peoplepro.user.core.domain.model.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.With;

@Getter
@EqualsAndHashCode
public class Endereco {
    @With
    String rua;
    @With
    String numero;
    @With
    String complemento;
    @With
    String bairro;
    @With
    String cidade;
    @With
    String estado;
    @With
    String cep;

    @Builder
    public Endereco(String rua,
                    String numero,
                    String complemento,
                    String bairro,
                    String cidade,
                    String estado,
                    String cep) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco update(String rua,
                           String numero,
                           String complemento,
                           String bairro,
                           String cidade,
                           String estado,
                           String cep) {
        return this.withRua(rua)
                .withNumero(numero)
                .withComplemento(complemento)
                .withBairro(bairro)
                .withCidade(cidade)
                .withEstado(estado)
                .withCep(cep);
    }
}
