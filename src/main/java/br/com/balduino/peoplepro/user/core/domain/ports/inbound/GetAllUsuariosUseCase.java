package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;

public interface GetAllUsuariosUseCase {
    Page<Usuario> getAllUsuarios(Integer page, Integer pageSize);
}
