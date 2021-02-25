package br.com.ifsul.pontoeletronico.repository;

import br.com.ifsul.pontoeletronico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUsuarioByName(String name);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findUsuariosByEquipe(String equipe);
}
