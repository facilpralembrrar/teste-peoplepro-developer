package br.com.balduino.peoplepro.user.core.domain.service;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.GetAllUsuariosUseCase;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.Page;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.QueryUsuarioOutputPort;

public class GetAllUsuariosService implements GetAllUsuariosUseCase {
    private final QueryUsuarioOutputPort queryUsuarioOutputPort;

    public GetAllUsuariosService(QueryUsuarioOutputPort queryUsuarioOutputPort) {
        this.queryUsuarioOutputPort = queryUsuarioOutputPort;
    }

    @Override
    public Page<Usuario> getAllUsuarios(Integer page, Integer pageSize) {
        return queryUsuarioOutputPort.getAllUsuarios(page, pageSize);
    }
}
