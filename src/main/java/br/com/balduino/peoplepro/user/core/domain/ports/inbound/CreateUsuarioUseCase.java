package br.com.balduino.peoplepro.user.core.domain.ports.inbound;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.model.vo.Endereco;

import java.time.LocalDate;

public interface CreateUsuarioUseCase {
    Usuario create(String cpf,
                   String nome,
                   LocalDate dataNascimento,
                   Endereco endereco);
}
