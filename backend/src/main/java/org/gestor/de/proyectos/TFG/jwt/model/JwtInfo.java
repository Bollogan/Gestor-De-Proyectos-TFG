package org.gestor.de.proyectos.TFG.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtInfo {

    private Long userId;

    private String userName;
}
