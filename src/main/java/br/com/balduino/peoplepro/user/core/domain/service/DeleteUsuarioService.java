package br.com.balduino.peoplepro.user.core.domain.service;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.model.UsuarioStatus;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.DeleteUsuarioUseCase;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.UsuarioNotFoundException;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.QueryUsuarioOutputPort;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.SaveUsuarioOutputPort;

import java.util.UUID;

public class DeleteUsuarioService implements DeleteUsuarioUseCase {
    private final QueryUsuarioOutputPort queryUsuarioOutputPort;
    private final SaveUsuarioOutputPort saveUsuarioOutputPort;

    public DeleteUsuarioService(QueryUsuarioOutputPort queryUsuarioOutputPort, SaveUsuarioOutputPort saveUsuarioOutputPort) {
        this.queryUsuarioOutputPort = queryUsuarioOutputPort;
        this.saveUsuarioOutputPort = saveUsuarioOutputPort;
    }

    @Override
    public void update(UUID usuarioId) {
        Usuario usuario = queryUsuarioOutputPort.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId))
                .withStatus(UsuarioStatus.INATIVO);

        saveUsuarioOutputPort.save(usuario);
    }
}
