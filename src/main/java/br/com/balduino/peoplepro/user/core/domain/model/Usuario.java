package br.com.balduino.peoplepro.user.core.domain.model;

import br.com.balduino.peoplepro.user.core.domain.Utils;
import br.com.balduino.peoplepro.user.core.domain.model.vo.Endereco;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode
public class Usuario {
    UUID id;
    @With
    String cpf;
    @With
    String nome;
    @With
    LocalDate dataNascimento;
    @With
    Endereco endereco;
    @With
    UsuarioStatus status;
    @With
    Integer version;
    @With
    LocalDateTime createdAt;
    @With
    String createdBy;
    @With
    LocalDateTime updatedAt;
    @With
    String updatedBy;

    @Builder
    public Usuario(UUID id,
                   String cpf,
                   String nome,
                   LocalDate dataNascimento,
                   Endereco endereco,
                   UsuarioStatus status,
                   Integer version,
                   LocalDateTime createdAt,
                   String createdBy,
                   LocalDateTime updatedAt,
                   String updatedBy) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.status = status;
        this.version = version;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public static Usuario newUsuario(
            String cpf,
            String nome,
            LocalDate dataNascimento,
            Endereco endereco,
            UsuarioStatus status) {

        return Usuario.builder()
                .id(UUID.randomUUID())
                .cpf(cpf)
                .nome(nome)
                .dataNascimento(dataNascimento)
                .endereco(endereco)
                .status(status)
                .build()
                .validate();
    }

    public Usuario update(String cpf,
                          String nome,
                          LocalDate dataNascimento,
                          Endereco endereco,
                          UsuarioStatus status) {
        return this
                .withCpf(cpf)
                .withNome(nome)
                .withDataNascimento(dataNascimento)
                .withEndereco(endereco)
                .withStatus(status)
                .validate();
    }

    private Usuario validate() {
        Utils.getInstance().validateObject("id", id);
        Utils.getInstance().validateString("cpf", cpf);
        Utils.getInstance().validateString("nome", nome);
        Utils.getInstance().validateObject("dataNascimento", dataNascimento);
        Utils.getInstance().validateObject("endereco", endereco);
        Utils.getInstance().validateString("endereco.rua", endereco.getRua());
        Utils.getInstance().validateString("endereco.numero", endereco.getNumero());
        Utils.getInstance().validateString("endereco.complemento", endereco.getComplemento());
        Utils.getInstance().validateString("endereco.bairro", endereco.getBairro());
        Utils.getInstance().validateString("endereco.cidade", endereco.getCidade());
        Utils.getInstance().validateString("endereco.estado", endereco.getEstado());
        Utils.getInstance().validateString("endereco.cep", endereco.getCep());

        return this;
    }
}
