package org.gestor.de.proyectos.TFG.project.dto;

import java.time.LocalDate;
import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.enums.EstadoProyecto;
import org.gestor.de.proyectos.TFG.rest.dtos.VersionDTO;
import org.gestor.de.proyectos.TFG.task.dto.TaskDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private EstadoProyecto status;

    private String description;

    private LocalDate startDate;

    private LocalDate estimatedEndDate;

    private ProjectDTO parentProject;

    private List<ProjectOverviewDTO> childProjects;

    private UserDTO createdBy;

    private List<TaskDTO> tasks;

    private List<VersionDTO> versions;
}
