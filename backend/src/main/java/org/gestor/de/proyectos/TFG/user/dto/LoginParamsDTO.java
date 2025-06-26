package org.gestor.de.proyectos.TFG.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginParamsDTO {

    private String userName;

    private String password;
}
