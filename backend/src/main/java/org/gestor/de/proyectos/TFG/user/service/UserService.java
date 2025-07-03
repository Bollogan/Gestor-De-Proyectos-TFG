package org.gestor.de.proyectos.TFG.user.service;

import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.user.errors.DuplicateInstanceException;
import org.gestor.de.proyectos.TFG.user.errors.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    void signUp(Usuario user) throws DuplicateInstanceException;

    Usuario login(String userName, String password) throws IncorrectLoginException;

    Usuario loginFromId(Long id) throws InstanceNotFoundException;

    Usuario findById(Long id);
    
    void updateUserTheme(Long userId, String theme) throws InstanceNotFoundException;

    UserDetails loadUserById(Long userId);
}
