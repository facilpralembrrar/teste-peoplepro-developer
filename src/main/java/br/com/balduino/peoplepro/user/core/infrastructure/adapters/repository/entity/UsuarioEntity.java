package br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.entity;

import br.com.balduino.peoplepro.user.core.domain.model.UsuarioStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
@Audited
@Data
public class UsuarioEntity {

    @Id
    @ColumnTransformer(read = "uuid_from_bin(id)")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    UUID id;

    @Column(name = "cpf", nullable = false, length = 15)
    String cpf;

    @Column(name = "nome", nullable = false, length = 256)
    String nome;

    @Column(name = "dataNascimento", nullable = false)
    LocalDateTime dataNascimento;

    @Embedded
    EnderecoEntity endereco;

    @Enumerated
    UsuarioStatus status;

    @Version
    @Column(name = "version", nullable = false)
    Integer version;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    String createdBy;

    @Column(name = "updated_at")
    @LastModifiedDate
    LocalDateTime updatedAt;

    @Column(name = "updatedBy", updatable = true)
    @LastModifiedBy
    String updatedBy;
}
