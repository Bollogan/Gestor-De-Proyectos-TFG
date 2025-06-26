package org.gestor.de.proyectos.TFG.rest.dtos;

import java.util.List;
import org.gestor.de.proyectos.TFG.common.dto.TaskAssignationDTO;
import org.gestor.de.proyectos.TFG.common.model.entities.AsignacionTarea;
import org.gestor.de.proyectos.TFG.user.mapper.UserMapper;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskAssignationMapper {

    public static final TaskAssignationDTO toTaskAssignationDTO(AsignacionTarea assignation) {
        return new TaskAssignationDTO(
                assignation.getId().getTareaId(),
                assignation.getId().getUsuarioId(),
                UserMapper.toUserDTO(assignation.getUsuario()),
                assignation.getFechaAsignacion(),
                assignation.getRolEnTarea()
        );
    }

    public static final List<TaskAssignationDTO> toTaskAssignationDTOs(List<AsignacionTarea> assignations) {
        return assignations.stream().map(TaskAssignationMapper::toTaskAssignationDTO).toList();
    }
}
