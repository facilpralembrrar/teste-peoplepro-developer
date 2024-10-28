package br.com.balduino.peoplepro.user.core.infrastructure.adapters;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.SaveUsuarioOutputPort;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.UsuarioRepository;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SavePlanAdapter implements SaveUsuarioOutputPort {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(entityMapper.toUsuarioEntity(usuario));
    }
}
