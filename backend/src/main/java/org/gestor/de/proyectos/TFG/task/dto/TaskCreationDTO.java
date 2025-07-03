package org.gestor.de.proyectos.TFG.task.dto;

import java.time.LocalDate;
import java.util.List;
import org.gestor.de.proyectos.TFG.common.dto.TaskAssignationDTO;
import org.gestor.de.proyectos.TFG.common.model.enums.EstadoTarea;
import org.gestor.de.proyectos.TFG.rest.dtos.VersionDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreationDTO {

    private String title;

    private String description;

    private Integer historyPoints;

    private Integer estimatedEffort;

    private LocalDate plannedStartDate;

    private LocalDate plannedEndDate;

    private LocalDate actualEndDate;

    private EstadoTarea status;

    private Long versionId;

    private Long principalResponsibleId;

    private List<TaskAssignationDTO> taskAssignations;
}
