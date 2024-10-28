package br.com.balduino.peoplepro.user.core.domain.ports.outbound;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.Page;

import java.util.Optional;
import java.util.UUID;

public interface QueryUsuarioOutputPort {
    Page<Usuario> getAllUsuarios(Integer page, Integer pageSize);

    Optional<Usuario> findById(UUID usuarioId);
}
