package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByNome(String autor);

    UserDetails findByEmail(String email);
}
