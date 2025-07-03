package org.gestor.de.proyectos.TFG.task.mapper;

import java.util.List;
import org.gestor.de.proyectos.TFG.common.dto.TaskAssignationDTO;
import org.gestor.de.proyectos.TFG.project.model.AsignacionTarea;
import org.gestor.de.proyectos.TFG.project.model.AsignacionTareaId;
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

    public static final AsignacionTarea toAsignacionTarea(TaskAssignationDTO assignationDTO) {
        AsignacionTarea asignacion = new AsignacionTarea();
        asignacion.setId(new AsignacionTareaId(assignationDTO.getTaskId(), assignationDTO.getUserId()));
        asignacion.setUsuario(UserMapper.toUsuario(assignationDTO.getUser()));
        asignacion.setFechaAsignacion(assignationDTO.getAssignmentDate());
        asignacion.setRolEnTarea(assignationDTO.getRoleOnTask());
        return asignacion;
    }

    public static final List<AsignacionTarea> toAsignacionTareas(List<TaskAssignationDTO> assignationDTOs) {
        return assignationDTOs.stream().map(TaskAssignationMapper::toAsignacionTarea).toList();
    }
}
