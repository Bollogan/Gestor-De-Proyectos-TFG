package org.gestor.de.proyectos.TFG.common.model.services;

import java.util.Optional;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import org.gestor.de.proyectos.TFG.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PermissionCheckerImpl implements PermissionChecker {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void checkUserExists(Long userId) throws InstanceNotFoundException {
        
        if (!userRepository.existsById(userId)) {
            throw new InstanceNotFoundException("project.entities.user", userId);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Usuario checkUser(Long userId) throws InstanceNotFoundException {

        Optional<Usuario> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new InstanceNotFoundException("project.entities.user", userId);
        }

        return user.get();

    }
}
