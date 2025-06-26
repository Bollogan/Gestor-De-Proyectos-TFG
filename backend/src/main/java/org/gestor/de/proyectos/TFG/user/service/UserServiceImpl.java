package org.gestor.de.proyectos.TFG.user.service;

import java.util.Optional;
import org.gestor.de.proyectos.TFG.common.model.exceptions.DuplicateInstanceException;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.common.model.services.PermissionChecker;
import org.gestor.de.proyectos.TFG.user.errors.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import org.gestor.de.proyectos.TFG.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionChecker permissionChecker;

    @Override
    public void signUp(Usuario user) throws DuplicateInstanceException {

        if (userRepository.existsByUsuario(user.getUsuario())) {
            throw new DuplicateInstanceException("project.entities.user", user.getUsuario());
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);

    }

    @Override
    @Transactional(readOnly = true)
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

    @Override
    public Usuario findById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }
}
