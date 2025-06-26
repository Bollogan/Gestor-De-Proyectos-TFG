package org.gestor.de.proyectos.TFG.project.dto;

import org.gestor.de.proyectos.TFG.common.model.enums.EstadoProyecto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectOverviewDTO {

    private Long id;

    private String name;

    private EstadoProyecto status; 

}
