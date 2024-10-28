package br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository;

import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
