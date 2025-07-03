package org.gestor.de.proyectos.TFG.task.mapper;

import java.util.List;
import org.gestor.de.proyectos.TFG.project.mapper.ProjectMapper;
import org.gestor.de.proyectos.TFG.task.dto.TaskDTO;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
import org.gestor.de.proyectos.TFG.user.mapper.UserMapper;
import org.gestor.de.proyectos.TFG.version.mapper.VersionMapper;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskMapper {

    public static final TaskDTO toTaskDTO(Tarea tarea) {
        return new TaskDTO(
                tarea.getId(),
                ProjectMapper.toProjectDTO(tarea.getProyecto()),
                tarea.getTitulo(),
                tarea.getDescripcion(),
                tarea.getPuntosHistoria(),
                tarea.getEsfuerzoEstimado(),
                tarea.getFechaInicioPlan(),
                tarea.getFechaFinPlan(),
                tarea.getFechaFinReal(),
                tarea.getEstado(),
                VersionMapper.toVersionDTO(tarea.getVersion()),
                UserMapper.toUserDTO(tarea.getResponsablePrincipal()),
                TaskAssignationMapper.toTaskAssignationDTOs(tarea.getAsignaciones())
            );
    }

    public static final List<TaskDTO> toTaskDTOs(List<Tarea> tareas) {
        return tareas.stream().map(TaskMapper::toTaskDTO).toList();
    }
}
