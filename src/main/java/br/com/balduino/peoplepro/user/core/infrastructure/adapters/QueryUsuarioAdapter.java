package br.com.balduino.peoplepro.user.core.infrastructure.adapters;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.Page;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.QueryUsuarioOutputPort;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.UsuarioRepository;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class QueryUsuarioAdapter implements QueryUsuarioOutputPort {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public Page<Usuario> getAllUsuarios(Integer page, Integer pageSize) {
        return entityMapper.toUsuarioPage(usuarioRepository.findAll(PageRequest.of(page, pageSize)));
    }

    @Override
    public Optional<Usuario> findById(UUID usuarioId) {
        return usuarioRepository.findById(usuarioId).map(u -> entityMapper.toUsuario(u));
    }
}
