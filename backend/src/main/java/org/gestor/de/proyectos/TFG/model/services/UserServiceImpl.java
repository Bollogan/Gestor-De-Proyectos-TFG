package org.gestor.de.proyectos.TFG.model.services;

import java.util.Optional;
import org.gestor.de.proyectos.TFG.model.entities.Usuario;
import org.gestor.de.proyectos.TFG.model.exceptions.DuplicateInstanceException;
import org.gestor.de.proyectos.TFG.model.exceptions.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionChecker permissionChecker;

    @Override
    public void signUp(Usuario user) throws DuplicateInstanceException {

        if (userRepository.existsByUsuario(user.getUsuario())) {
            throw new DuplicateInstanceException("project.entities.user", user.getUsuario());
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);

    }

    @Override
    public Usuario login(String username, String password) throws IncorrectLoginException {

        Optional<Usuario> user = userRepository.findByUsuario(username);

        if (!user.isPresent()) {
            throw new IncorrectLoginException(username, password);
        }

        if (!passwordEncoder.matches(password, user.get().getPasswordHash())) {
            throw new IncorrectLoginException(username, password);
        }

        return user.get();
    }

    @Override
    public Usuario loginFromId(Long id) throws InstanceNotFoundException {
        return permissionChecker.checkUser(id);
    }
}
