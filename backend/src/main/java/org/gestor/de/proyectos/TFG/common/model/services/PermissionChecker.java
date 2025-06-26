package org.gestor.de.proyectos.TFG.common.model.services;

import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.user.model.Usuario;

public interface PermissionChecker {

    public void checkUserExists(Long userId) throws InstanceNotFoundException;

    public Usuario checkUser(Long userId) throws InstanceNotFoundException;
    
}
