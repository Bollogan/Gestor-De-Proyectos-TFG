package org.gestor.de.proyectos.TFG.rest.common;

import lombok.Data;

@Data
public class FieldErrorDTO {

    private String fieldName;

    private String message;
}
