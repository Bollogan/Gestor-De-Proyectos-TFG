package org.gestor.de.proyectos.TFG.project.mapper;

import java.util.List;
import org.gestor.de.proyectos.TFG.project.dto.ProjectDTO;
import org.gestor.de.proyectos.TFG.project.dto.ProjectOverviewDTO;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import org.gestor.de.proyectos.TFG.task.mapper.TaskMapper;
import org.gestor.de.proyectos.TFG.user.mapper.UserMapper;
import org.gestor.de.proyectos.TFG.version.mapper.VersionMapper;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProjectMapper {

    public static final ProjectDTO toProjectDTO(Proyecto project) {
        return new ProjectDTO(
            project.getId(),
            project.getNombre(),
            project.getEstado(),
            project.getDescripcion(),
            project.getFechaInicio(),
            project.getFechaFinEstimada(),
            ProjectMapper.toProjectDTO(project.getProyectoPadre()),
            ProjectMapper.toProjectOverviewDTOs(project.getSubproyectos()),
            UserMapper.toUserDTO(project.getCreadoPor()),
            TaskMapper.toTaskDTOs(project.getTareas()),
            VersionMapper.toVersionDTOs(project.getVersiones())
        );
    }

    public static final List<ProjectDTO> toProjectDTOs(List<Proyecto> projects) {
        return projects.stream().map(ProjectMapper::toProjectDTO).toList();
    }

    public static final ProjectOverviewDTO toProjectOverviewDTO(Proyecto project) {
        return new ProjectOverviewDTO(
            project.getId(),
            project.getNombre(),
            project.getEstado()
        );
    }

    public static final List<ProjectOverviewDTO> toProjectOverviewDTOs(List<Proyecto> projects) {
        return projects.stream().map(ProjectMapper::toProjectOverviewDTO).toList();
    }
}
