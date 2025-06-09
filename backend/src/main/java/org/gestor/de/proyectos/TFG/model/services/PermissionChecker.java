package org.gestor.de.proyectos.TFG.model.services;

import org.gestor.de.proyectos.TFG.model.entities.Usuario;
import org.gestor.de.proyectos.TFG.model.exceptions.commons.InstanceNotFoundException;

public interface PermissionChecker {

    public void checkUserExists(Long userId) throws InstanceNotFoundException;

    public Usuario checkUser(Long userId) throws InstanceNotFoundException;
    
}
