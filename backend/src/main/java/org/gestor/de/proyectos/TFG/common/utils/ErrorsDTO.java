package org.gestor.de.proyectos.TFG.common.utils;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorsDTO {

    private String globalError;

    private List<FieldErrorDTO> fieldErrors;

    public ErrorsDTO(String globalError) {
        this.globalError = globalError;
    }

    public ErrorsDTO(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
