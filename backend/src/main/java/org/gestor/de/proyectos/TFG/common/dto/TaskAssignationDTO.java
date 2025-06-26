package org.gestor.de.proyectos.TFG.common.dto;

import java.time.LocalDateTime;
import org.gestor.de.proyectos.TFG.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TaskAssignationDTO {

    private Long taskId;

    private Long userId;

    private UserDTO user;

    private LocalDateTime assignmentDate;

    private String roleOnTask;
}
