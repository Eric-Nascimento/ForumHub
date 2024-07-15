package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByNome(String autor);

    Optional<Usuario> findByEmail(String email);
}
