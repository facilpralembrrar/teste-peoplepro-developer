package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

import java.util.UUID;

public class UsuarioNotFoundException extends ModelNotFoundException {

    public UsuarioNotFoundException(UUID usuarioId) {
        super(String.format("Usuário not found. Usuário ID = %s", usuarioId));
    }
}
