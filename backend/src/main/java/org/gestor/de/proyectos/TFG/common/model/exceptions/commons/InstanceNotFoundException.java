package org.gestor.de.proyectos.TFG.common.model.exceptions.commons;

public class InstanceNotFoundException extends InstanceException {

    public InstanceNotFoundException(String name, Object key) {
        super(name, key);
    }
    
}
