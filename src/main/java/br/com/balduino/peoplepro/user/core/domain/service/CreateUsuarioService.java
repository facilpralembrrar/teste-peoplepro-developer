package br.com.balduino.peoplepro.user.core.domain.service;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.model.UsuarioStatus;
import br.com.balduino.peoplepro.user.core.domain.model.vo.Endereco;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.CreateUsuarioUseCase;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.SaveUsuarioOutputPort;

import java.time.LocalDate;

public class CreateUsuarioService implements CreateUsuarioUseCase {
    private final SaveUsuarioOutputPort saveUsuarioOutputPort;

    public CreateUsuarioService(SaveUsuarioOutputPort saveUsuarioOutputPort) {
        this.saveUsuarioOutputPort = saveUsuarioOutputPort;
    }

    @Override
    public Usuario create(String cpf,
                          String nome,
                          LocalDate dataNascimento,
                          Endereco endereco) {
        Usuario newUsuario = Usuario.newUsuario(cpf, nome, dataNascimento, endereco, UsuarioStatus.ATIVO);

        saveUsuarioOutputPort.save(newUsuario);

        return newUsuario;
    }
}
