package org.gestor.de.proyectos.TFG.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String email;

    private Boolean active;

    public UserDTO(Long id, String userName, String password, String firstName, String email, Boolean active) {

        this.id = id;
        this.username = userName.trim();
        this.password = password;
        this.firstName = firstName.trim();
        this.email = email.trim();
        this.active = active != null ? active : false;
    }
}
