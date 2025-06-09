package org.gestor.de.proyectos.TFG.model.services;

import java.util.Optional;
import org.gestor.de.proyectos.TFG.model.entities.Usuario;
import org.gestor.de.proyectos.TFG.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionCheckerImpl implements PermissionChecker {

    @Autowired
    UserRepository userRepository;

    @Override
    public void checkUserExists(Long userId) throws InstanceNotFoundException {
        
        if (!userRepository.existsById(userId)) {
            throw new InstanceNotFoundException("project.entities.user", userId);
        }

    }

    @Override
    public Usuario checkUser(Long userId) throws InstanceNotFoundException {

        Optional<Usuario> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new InstanceNotFoundException("project.entities.user", userId);
        }

        return user.get();

    }
}
