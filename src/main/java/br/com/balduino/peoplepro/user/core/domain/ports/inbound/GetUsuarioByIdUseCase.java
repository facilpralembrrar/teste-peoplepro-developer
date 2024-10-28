package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;

import java.util.UUID;

public interface GetUsuarioByIdUseCase {
    Usuario getUsuarioById(UUID usuarioId);
}
