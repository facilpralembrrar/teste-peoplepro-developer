package br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.mapper;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.model.vo.Endereco;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.UsuarioPageImpl;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.entity.EnderecoEntity;
import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    UsuarioEntity toUsuarioEntity(Usuario usuario);

    EnderecoEntity toEnderecoEntity(Endereco endereco);

    Usuario toUsuario(UsuarioEntity usuarioEntity);

    Endereco toEndereco(EnderecoEntity enderecoEntity);

    List<Usuario> toUsuarioList(List<UsuarioEntity> usuarioEntities);

    default UsuarioPageImpl toUsuarioPage(Page<UsuarioEntity> page) {
        UsuarioPageImpl usuarioPage = new UsuarioPageImpl();
        usuarioPage.setNumber(page.getNumber());
        usuarioPage.setSize(page.getSize());
        usuarioPage.setContent(toUsuarioList(page.getContent()));
        usuarioPage.setNumberOfElements(page.getNumberOfElements());
        usuarioPage.setTotalElements(page.getTotalElements());
        usuarioPage.setTotalPages(page.getTotalPages());

        return usuarioPage;
    }
}
