package org.gestor.de.proyectos.TFG.task.dto;

import java.time.LocalDate;
import java.util.List;
import org.gestor.de.proyectos.TFG.common.dto.TaskAssignationDTO;
import org.gestor.de.proyectos.TFG.common.model.enums.EstadoTarea;
import org.gestor.de.proyectos.TFG.project.dto.ProjectDTO;
import org.gestor.de.proyectos.TFG.rest.dtos.VersionDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    private ProjectDTO project;

    private String title;

    private String description;

    private Integer historyPoints;

    private Integer estimatedEffort;

    private LocalDate plannedStartDate;

    private LocalDate plannedEndDate;

    private LocalDate actualEndDate;

    private EstadoTarea status;

    private VersionDTO version;

    private UserDTO principalResponsible;

    private List<TaskAssignationDTO> taskAssignations;
}
