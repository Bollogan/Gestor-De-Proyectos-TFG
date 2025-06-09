package org.gestor.de.proyectos.TFG.model.exceptions;

import org.gestor.de.proyectos.TFG.model.exceptions.commons.InstanceException;

public class DuplicateInstanceException extends InstanceException {

    public DuplicateInstanceException(String name, Object key) {
        super(name, key);
    }

}
