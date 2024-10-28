package br.com.balduino.peoplepro.user.core.application.rest.mapper;

import br.com.balduino.peoplepro.user.core.application.rest.request.CreateUsuarioEnderecoRequest;
import br.com.balduino.peoplepro.user.core.application.rest.request.UpdateUsuarioEnderecoRequest;
import br.com.balduino.peoplepro.user.core.application.rest.response.CreateUsuarioResponse;
import br.com.balduino.peoplepro.user.core.application.rest.response.GetAllUsuariosResponse;
import br.com.balduino.peoplepro.user.core.application.rest.response.GetUsuarioByIdResponse;
import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.model.vo.Endereco;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.Page;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    GetUsuarioByIdResponse toGetUsuarioByIdResponse(Usuario usuario);

    GetAllUsuariosResponse toGetAllUsuariosResponse(Page<Usuario> usuarioPage);

    CreateUsuarioResponse toCreateUsuarioResponse(Usuario usuario);

    Endereco toEndereco(CreateUsuarioEnderecoRequest createUsuarioEnderecoRequest);

    Endereco toEndereco(UpdateUsuarioEnderecoRequest updateUsuarioEnderecoRequest);
}
