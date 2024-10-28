package br.com.balduino.peoplepro.user.core.domain.service;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.GetUsuarioByIdUseCase;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.UsuarioNotFoundException;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.QueryUsuarioOutputPort;

import java.util.UUID;

public class GetUsuarioByIdService implements GetUsuarioByIdUseCase {
    private final QueryUsuarioOutputPort queryUsuarioOutputPort;

    public GetUsuarioByIdService(QueryUsuarioOutputPort queryUsuarioOutputPort) {
        this.queryUsuarioOutputPort = queryUsuarioOutputPort;
    }

    @Override
    public Usuario getUsuarioById(UUID usuarioId) {
        return queryUsuarioOutputPort.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));
    }
}
