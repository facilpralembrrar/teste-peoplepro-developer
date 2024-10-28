package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

import java.util.UUID;

public interface DeleteUsuarioUseCase {
    void update(UUID usuarioId);
}
