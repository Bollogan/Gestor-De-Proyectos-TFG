package org.gestor.de.proyectos.TFG.common.model.exceptions.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class InstanceException extends Exception{

    private final String name;

    private final transient Object key;

}
