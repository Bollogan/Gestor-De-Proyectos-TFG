package org.gestor.de.proyectos.TFG.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUserDTO {

    private String serviceToken;

    private UserDTO userDto;
}
