package org.gestor.de.proyectos.TFG.model.exceptions.commons;

public class InstanceNotFoundException extends InstanceException {

    public InstanceNotFoundException(String name, Object key) {
        super(name, key);
    }
    
}
