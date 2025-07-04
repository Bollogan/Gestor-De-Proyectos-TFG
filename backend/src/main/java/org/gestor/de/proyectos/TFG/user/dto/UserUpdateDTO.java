package org.gestor.de.proyectos.TFG.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDTO {

    private String username;

    private String name;

    private String email;
}
