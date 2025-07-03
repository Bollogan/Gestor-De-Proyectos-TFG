package org.gestor.de.proyectos.TFG.project.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionTareaId implements Serializable{
    private Long tareaId;
    private Long usuarioId;
}
