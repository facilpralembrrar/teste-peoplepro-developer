package br.com.balduino.peoplepro.user.core.domain.service;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.model.UsuarioStatus;
import br.com.balduino.peoplepro.user.core.domain.model.vo.Endereco;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.UpdateUsuarioUseCase;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.UsuarioNotFoundException;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.QueryUsuarioOutputPort;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.SaveUsuarioOutputPort;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateUsuarioService implements UpdateUsuarioUseCase {
    private final QueryUsuarioOutputPort queryUsuarioOutputPort;
    private final SaveUsuarioOutputPort saveUsuarioOutputPort;

    public UpdateUsuarioService(QueryUsuarioOutputPort queryUsuarioOutputPort,
                                SaveUsuarioOutputPort saveUsuarioOutputPort) {
        this.queryUsuarioOutputPort = queryUsuarioOutputPort;
        this.saveUsuarioOutputPort = saveUsuarioOutputPort;
    }

    @Override
    public void update(UUID usuarioId,
                       String cpf,
                       String nome,
                       LocalDate dataNascimento,
                       Endereco endereco,
                       UsuarioStatus status) {
        Usuario usuario = queryUsuarioOutputPort.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId))
                .update(cpf, nome, dataNascimento, endereco, status);

        saveUsuarioOutputPort.save(usuario);
    }
}
