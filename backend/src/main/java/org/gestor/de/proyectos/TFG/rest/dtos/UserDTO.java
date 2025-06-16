package org.gestor.de.proyectos.TFG.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String nombre;

    private String email;

    private Boolean activo;
}
