package org.gestor.de.proyectos.TFG.version.dto;

import java.time.LocalDate;
import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.enums.EstadoVersion;
import org.gestor.de.proyectos.TFG.project.dto.ProjectDTO;
import org.gestor.de.proyectos.TFG.task.dto.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VersionDTO {

    private Long id;

    private ProjectDTO project;

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate deliveryDate;

    private EstadoVersion status;

    private List<TaskDTO> tasks;

}
