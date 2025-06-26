package org.gestor.de.proyectos.TFG.user.repository;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByUsuario(String usuario);

    Optional<Usuario> findByUsuario(String usuario);
}
