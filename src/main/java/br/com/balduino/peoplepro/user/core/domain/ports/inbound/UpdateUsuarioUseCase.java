package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

import br.com.balduino.peoplepro.user.core.domain.model.UsuarioStatus;
import br.com.balduino.peoplepro.user.core.domain.model.vo.Endereco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

public interface UpdateUsuarioUseCase {
    void update(UUID usuarioId,
                String cpf,
                String nome,
                LocalDate dataNascimento,
                Endereco endereco,
                UsuarioStatus status);
}
