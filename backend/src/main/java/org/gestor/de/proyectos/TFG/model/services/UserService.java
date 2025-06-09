package org.gestor.de.proyectos.TFG.model.services;

import org.gestor.de.proyectos.TFG.model.entities.Usuario;
import org.gestor.de.proyectos.TFG.model.exceptions.DuplicateInstanceException;
import org.gestor.de.proyectos.TFG.model.exceptions.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.model.exceptions.commons.InstanceNotFoundException;

public interface UserService {

    void signUp(Usuario user) throws DuplicateInstanceException;

    Usuario login(String userName, String password) throws IncorrectLoginException;

    Usuario loginFromId(Long id) throws InstanceNotFoundException;
}
