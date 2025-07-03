package org.gestor.de.proyectos.TFG.project.service;

import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.enums.EstadoProyecto;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.project.dto.ProjectCreationDTO;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import org.gestor.de.proyectos.TFG.user.errors.PermissionException;

public interface ProjectService {

    List<Proyecto> getAllProjectsFromUserId(Long userId);
    
    Proyecto getProjectById(Long projectId) throws InstanceNotFoundException;

    Proyecto createProject(ProjectCreationDTO project, Long projectId, Long userId) throws InstanceNotFoundException;

    Proyecto updateProject(ProjectCreationDTO project, Long projectId, Long userId) throws InstanceNotFoundException, PermissionException;

    Proyecto changeProjectStatus(Long projectId, EstadoProyecto status) throws InstanceNotFoundException;

    void deleteProject(Long projectId) throws InstanceNotFoundException;

    Proyecto addTaskToProject(Long projectId, Long taskId) throws InstanceNotFoundException;
    
    Proyecto addVersionToProject(Long projectId, Long versionId) throws InstanceNotFoundException;
}
