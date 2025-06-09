package org.gestor.de.proyectos.TFG.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IncorrectLoginException extends Exception {

    private final String userName;

    private final String password;
}
