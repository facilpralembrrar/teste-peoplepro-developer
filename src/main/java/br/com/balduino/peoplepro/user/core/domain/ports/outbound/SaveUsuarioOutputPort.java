package br.com.balduino.peoplepro.user.core.domain.ports.outbound;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;

public interface SaveUsuarioOutputPort {
    void save(Usuario plan);
}
