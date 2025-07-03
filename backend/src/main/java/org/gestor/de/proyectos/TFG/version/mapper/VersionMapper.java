package org.gestor.de.proyectos.TFG.version.mapper;

import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.entities.Version;
import org.gestor.de.proyectos.TFG.project.mapper.ProjectMapper;
import org.gestor.de.proyectos.TFG.task.mapper.TaskMapper;
import org.gestor.de.proyectos.TFG.version.dto.VersionDTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VersionMapper {

    public static final VersionDTO toVersionDTO(Version version) {
        return new VersionDTO(
                version.getId(),
                ProjectMapper.toProjectDTO(version.getProyecto()),
                version.getNombre(),
                version.getDescripcion(),
                version.getFechaInicio(),
                version.getFechaEntrega(),
                version.getEstado(),
                TaskMapper.toTaskDTOs(version.getTareas())
        );
    }

    public static final List<VersionDTO> toVersionDTOs(List<Version> versiones) {
        return versiones.stream().map(VersionMapper::toVersionDTO).toList();
    }
}
