package br.com.balduino.peoplepro.user.core.infrastructure.adapters;

import br.com.balduino.peoplepro.user.core.domain.model.Usuario;
import br.com.balduino.peoplepro.user.core.domain.ports.inbound.Page;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioPageImpl implements Page<Usuario> {
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private int numberOfElements;
    private List<Usuario> content;
}
